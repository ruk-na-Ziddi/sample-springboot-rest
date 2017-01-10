package hello;

import apirequest.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class CartController {

    private HashMap<Integer, Cart> carts = new HashMap<>();

    @RequestMapping(method = RequestMethod.POST, path = "/carts")
    public Cart createCart(@RequestBody User user) {
        Integer id = user.getId();
        Cart cart = new Cart(id);
        carts.put(id, cart);
        return cart;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/carts/{userId}")
    @ResponseBody
    public Cart getCartDetails(@PathVariable ("userId") Integer _userId){
        return carts.get(_userId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/carts/{userId}")
    public Cart addItem(@RequestBody Item item, @PathVariable ("userId") Integer _userId){
        Cart cart = carts.get(_userId);
        cart.add(item);
        return cart;
    }
}
