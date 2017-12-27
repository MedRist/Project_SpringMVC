package me.project.perso.web;

import me.project.perso.dao.ProductRepository;
import me.project.perso.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @RequestMapping(value = "/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int p,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "mc" ,defaultValue = "") String mc)
    {
        Page<Product> products= productRepository.search("%"+mc+"%",new PageRequest(p,size));
        int []pages = new int [products.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("listProducts",products.getContent());
        model.addAttribute("size",size);
        model.addAttribute("currentPage",p);
        model.addAttribute("mc",mc);
        return "produits";
    }


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") Long id,
                         int page, int size, String mc)
    {
        productRepository.delete(id);
        return "redirect:/index?page="+page+"&size="+size+"&mc="+mc;

    }


    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String form(Model model)
    {
        model.addAttribute("product",new Product());
        return "form";

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Model model,
                       @Valid Product product,
                       BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "form";

        }
        productRepository.save(product);
        model.addAttribute("product",product);
        return "Confirmation";

    }
}
