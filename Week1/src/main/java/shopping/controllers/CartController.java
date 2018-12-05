package shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shopping.models.CartElement;
import shopping.models.User;
import shopping.services.CartService;
import shopping.services.ProductService;
import shopping.models.Cart;

@RestController
@RequestMapping("/api")
public class CartController {    
    @Autowired
    private CartService cart_s;
    @Autowired
    private ProductService product_s;

    //Cart mapping
    @GetMapping("/cart/{id}")
    public List<CartElement> getCartElements(@PathVariable("id") Long Id){
        return cart_s.getCartElements(Id); 
    }
    

    @GetMapping("/cart")                                                        
    public List<Cart> getAllCarts(){
        return cart_s.getCarts();
    }

    @PostMapping("/cart/{id}")                                             //new cart
    public Cart newCart(@PathVariable("id") Long userId,@RequestBody Cart cart){ 
        cart_s.addNewCart(userId,cart);
        return cart;
    }
    @PutMapping("/cart/{id}")                                           //edit cart
    public Cart editCart(@PathVariable("id") Long  Id,@RequestBody Cart cart){ 
        cart_s.editCart(Id,cart);
        return cart;
    }

    @DeleteMapping("/cart/{id}")
        public Cart deleteCart(@PathVariable("id") Long  Id){
           return cart_s.deleteCart(Id);
        }

    @GetMapping("/cart/{id}/user")
    public User getCartUser(@PathVariable("id") Long id){
        return cart_s.getCartUser(id);
    }
    
    //Elements Mapping

    
    @PutMapping("/cart/{c_id}/element/{p_id}/{quantity}")
    public CartElement addElementToCart(@PathVariable("c_id") Long CartId,@PathVariable("p_id")Long  ProductId,@PathVariable("quantity") int quantity){
       return cart_s.addElementToCart(CartId, product_s.getProductById(ProductId), quantity);       
    }
    @DeleteMapping("/cart/{c_id}/element{p_id}")
        public CartElement removeElementFromCart(@PathVariable("c_id") Long  CartId,@PathVariable("e_id")Long ElementId ){
           return cart_s.removeElementFromCart(CartId,ElementId);
        }
    }