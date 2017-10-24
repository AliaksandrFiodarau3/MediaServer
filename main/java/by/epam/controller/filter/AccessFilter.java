package by.epam.controller.filter;

import by.epam.constant.Attribute;
import by.epam.constant.CommandName;
import by.epam.constant.Parameter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AccessFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);

    private static final char OLD_CHAR = '-';
    private static final char NEW_CHAR = '_';

    private Set<CommandName> commandSetForUnauthorizedUser = new HashSet<>();


    /**
     * Method is used to restrict access to pages for unauthorized users
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("AccessFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String root = (String) httpRequest.getSession().getAttribute(Attribute.ATTRIBUTE_USER_ROLE);

        String command = httpRequest.getParameter(Parameter.PARMETER_COMMAND);


        if ((root == null ) && command != null) {

            try {
                CommandName commandName = CommandName.valueOf(command.toUpperCase().replace(OLD_CHAR, NEW_CHAR));
                boolean isPermited = commandSetForUnauthorizedUser.contains(commandName);

                if (!isPermited) {
                    LOGGER.info( "Unathorized attempt to execute command: " + commandName);
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                } else {
                    filterChain.doFilter(request, response);
                }

            } catch (IllegalArgumentException e) {
                LOGGER.info(e);
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            filterChain.doFilter(request, response);
        }
      /*  HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute(Attribute.ATTRIBUTE_USER);

        String command = req.getParameter(Parameter.PARMETER_COMMAND);

        if (command != null) {

            command = command.replace(OLD_CHAR, NEW_CHAR);
            CommandName commandName = CommandName.valueOf(command.toUpperCase());

            if (user == null) {
                if (commandGuestList.contains(commandName)) {
                    filterChain.doFilter(req, resp);
                } else {
                    request.getRequestDispatcher(Path.PATH_TO_INDEX_PAGE).forward(request, response);
                }
            } else {
                if (user.isAdminRoot() && commandAdminList.contains(commandName)) {
                    filterChain.doFilter(req, resp);
                }
                if (!user.isAdminRoot() && commandSetForUnauthorizedUser.contains(commandName)) {
                    filterChain.doFilter(req, resp);
                }
                else{
                    request.getRequestDispatcher(Path.PATH_TO_ERROR_PAGE).forward(request, response);
                }
            }
        }else{
            ((HttpServletResponse) response).sendRedirect(Path.PATH_TO_HOME_PAGE);
        }
        filterChain.doFilter(req, resp);*/
    }


    /**
     * @see Filter#init(FilterConfig)
     */

    public void init(FilterConfig filterConfig) throws ServletException {

       /* commandAdminList = new ArrayList<>();*/

        commandSetForUnauthorizedUser.add(CommandName.HOME);
        commandSetForUnauthorizedUser.add(CommandName.SIGN_IN);
        commandSetForUnauthorizedUser.add(CommandName.SIGN_UP);
        commandSetForUnauthorizedUser.add(CommandName.SIGN_OUT);
        commandSetForUnauthorizedUser.add(CommandName.CHANGE_LOCALE);
        commandSetForUnauthorizedUser.add(CommandName.CHECK_EMAIL);
        commandSetForUnauthorizedUser.add(CommandName.CHECK_LOGIN);


       /* /////////////////////////////////////////////////

        commandAdminList.add(CommandName.CHANGE_LOCALE);
        commandAdminList.add(CommandName.SIGN_OUT);

        commandAdminList.add(CommandName.ADD_ALBUM);
        commandAdminList.add(CommandName.ADD_ARTIST);
        commandAdminList.add(CommandName.ADD_BONUS);
        commandAdminList.add(CommandName.ADD_GENRE);
        commandAdminList.add(CommandName.ADD_ORDER);
        commandAdminList.add(CommandName.ADD_SONG);
        commandAdminList.add(CommandName.ADD_USER);

        commandAdminList.add(CommandName.SHOW_ALBUM);
        commandAdminList.add(CommandName.SHOW_ARTIST);
        commandAdminList.add(CommandName.SHOW_BONUS);
        commandAdminList.add(CommandName.SHOW_GENRE);
        commandAdminList.add(CommandName.SHOW_ORDER);
        commandAdminList.add(CommandName.SHOW_SONG);
        commandAdminList.add(CommandName.SHOW_USER);

        commandAdminList.add(CommandName.EDIT_ALBUM);
        commandAdminList.add(CommandName.EDIT_ARTIST);
        commandAdminList.add(CommandName.EDIT_BONUS);
        commandAdminList.add(CommandName.EDIT_GENRE);
        commandAdminList.add(CommandName.EDIT_ORDER);
        commandAdminList.add(CommandName.EDIT_SONG);
        commandAdminList.add(CommandName.EDIT_USER);

        commandAdminList.add(CommandName.DELETE_ALBUM);
        commandAdminList.add(CommandName.DELETE_ARTIST);
        commandAdminList.add(CommandName.DELETE_BONUS);
        commandAdminList.add(CommandName.DELETE_GENRE);
        commandAdminList.add(CommandName.DELETE_ORDER);
        commandAdminList.add(CommandName.DELETE_SONG);
        commandAdminList.add(CommandName.DELETE_USER);


        *//*commandSetForUnauthorizedUser = new ArrayList<>();*//*

        commandSetForUnauthorizedUser.add(CommandName.CHANGE_LOCALE);
        commandAdminList.add(CommandName.SIGN_OUT);

        commandSetForUnauthorizedUser.add(CommandName.ALBUM_SHOW);
        commandSetForUnauthorizedUser.add(CommandName.ARTIST_SHOW);


       *//* commandGuestList = new ArrayList<>();*//*

        commandGuestList.add(CommandName.CHANGE_LOCALE);
        commandGuestList.add(CommandName.SIGN_IN);*/

    }

    /**
     * @see Filter#destroy()
     */

    public void destroy() {
       // commandAdminList = null;
        commandSetForUnauthorizedUser = null;
      //  commandGuestList = null;
    }
}
