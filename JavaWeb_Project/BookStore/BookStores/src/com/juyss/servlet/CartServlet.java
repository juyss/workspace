package com.juyss.servlet;

import com.juyss.pojo.Book;
import com.juyss.pojo.Cart;
import com.juyss.pojo.CartItem;
import com.juyss.service.BookService;
import com.juyss.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: CartServlet
 * @Desc: 购物车Servlet层
 * @package com.juyss.servlet
 * @project BookStore
 * @date 2020/8/22 17:45
 */
public class CartServlet extends BaseServlet {

    private final BookService bookService = new BookServiceImpl();

    /**
     * 向购物车中添加指定商品
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException e
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            int bookId = Integer.parseInt(id);
            Book book = bookService.queryBookById(bookId);
            CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }
            cart.addItem(cartItem);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 删除购物车中的指定商品
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException e
     */
    protected void deleteItem(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
        String id = req.getParameter("id");
        if (id!=null){
            int bookId = Integer.parseInt(id);
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if (cart!=null){
                cart.deleteItem(bookId);
            }
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    protected void clearItem(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clearItem();
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    protected void updateItem(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
        String bookId = req.getParameter("id");
        String bookCount = req.getParameter("count");
        int id = Integer.parseInt(bookId);
        int count = Integer.parseInt(bookCount);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            cart.updateItemCount(id,count);
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");

    }
}
