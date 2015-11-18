package pl.java.scalatech.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("shopCart")
@Scope(value = "request")
@RequestMapping(value = "/shopCart")
public class ShopCartController {
	private final static String VIEW_NAME = "shopCart";

	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHandle(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("shopCart", shoppingCart);
		model.addAttribute("products", shoppingCart.getProducts());
		return new ModelAndView(VIEW_NAME, model.asMap());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getHandlePost(@Valid @ModelAttribute("shopCart") ShoppingCart shopCart, BindingResult result, SessionStatus status,
			HttpServletRequest request, HttpServletResponse response) {
		if (result.hasErrors())
			return new ModelAndView(VIEW_NAME);
		shopCart.getProducts().put(shopCart.getProduct(), shopCart.getTotal());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("shopCart", shopCart);
		model.put("products", shopCart.getProducts());
		status.setComplete();
		return new ModelAndView("redirect:/shopCart/");
	}
}
