package hello;

import apirequest.User;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api")
public class CartController {
    private AmazonS3 amazonS3;
    private String bucketName;
    private String key;

    @PostConstruct
    public void setUp() throws IOException {
        amazonS3 = getS3Client();
        saveCartMap(new HashMap<>());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/carts")
    public Cart createCart(@RequestBody User user) {
        Integer id = user.getId();
        Cart cart = new Cart(id);
        HashMap<Integer, Cart> cartMap = getCartMap();
        cartMap.put(id, cart);
        saveCartMap(cartMap);
        return cart;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/carts/{userId}")
    @ResponseBody
    public Cart getCartDetails(@PathVariable("userId") Integer _userId) {
        return getCartMap().get(_userId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/carts/{userId}")
    public Cart addItem(@RequestBody Item item, @PathVariable("userId") Integer _userId) {
        HashMap<Integer,Cart> carts = getCartMap();
        Cart cart = carts.get(_userId);
        cart.add(item);
        saveCartMap(carts);
        return cart;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/test")
    public MyObject getObject() {
        return new MyObject("myObject", asList(new MyChildObject(2)));
    }

    private void saveCartMap(HashMap<Integer, Cart> carts) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = null;

            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(carts);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bucketName = "<BUCKET_NAME>";
        key = "cart.se";
        amazonS3.putObject(new PutObjectRequest(bucketName, key, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), new ObjectMetadata()));
    }

    public AmazonS3 getS3Client() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("<ACCESS_KEY>", "<SECRET_KEY>");
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public HashMap<Integer, Cart> getCartMap() {
        S3Object object = amazonS3.getObject(new GetObjectRequest(bucketName, key));
        S3ObjectInputStream objectContent = object.getObjectContent();

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(objectContent);
            return (HashMap<Integer, Cart>) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
