package com.epam.mediaserver.controller.filter;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.CommandName;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.constant.Path;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessFilter implements Filter {

    private static final char OLD_CHAR = '-';
    private static final char NEW_CHAR = '_';

    private Set<CommandName> commandSetForUnauthorizedUser = new HashSet<>();
    private Set<CommandName> commandSetForAdmin = new HashSet<>();
    private Set<CommandName> commandSetForUser = new HashSet<>();


    /**
     * Method is used to restrict access to pages for unauthorized users
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String login = (String) req.getSession().getAttribute(Attribute.ATTRIBUTE_USER_LOGIN);
        String root = (String) req.getSession().getAttribute(Attribute.ATTRIBUTE_USER_ROLE);

        String command = req.getParameter(Parameter.PARMETER_COMMAND);

        if (command != null) {

            command = command.replace(OLD_CHAR, NEW_CHAR);
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            if (login != null) {
                if (Objects.deepEquals(root, "true") && commandSetForAdmin.contains(commandName)) {
                    System.out.println(root.toString());
                    filterChain.doFilter(req, resp);
                }

                if (Objects.deepEquals(root, "false") && commandSetForUser.contains(commandName)) {
                    System.out.println(root.toString());
                    filterChain.doFilter(req, resp);

                }
            } else {
                if (commandSetForUnauthorizedUser.contains(commandName)) {
                    filterChain.doFilter(req, resp);
                } else {
                    resp.sendRedirect(Path.PATH_TO_INDEX_PAGE);
                }
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }


    /**
     * @see Filter#init(FilterConfig)
     */

    public void init(FilterConfig filterConfig) {

        /**
         * Guest commands
         * */

        commandSetForUnauthorizedUser.add(CommandName.HOME);
        commandSetForUnauthorizedUser.add(CommandName.SIGN_IN);
        commandSetForUnauthorizedUser.add(CommandName.SIGN_UP);
        commandSetForUnauthorizedUser.add(CommandName.SIGN_OUT);
        commandSetForUnauthorizedUser.add(CommandName.CHANGE_LOCALE);
        commandSetForUnauthorizedUser.add(CommandName.CHECK_EMAIL);
        commandSetForUnauthorizedUser.add(CommandName.CHECK_LOGIN);

        /**
         * Admin commands
         * */

        commandSetForAdmin.add(CommandName.HOME);
        commandSetForAdmin.add(CommandName.CHANGE_LOCALE);
        commandSetForAdmin.add(CommandName.SIGN_OUT);
        commandSetForAdmin.add(CommandName.SIGN_IN);
        commandSetForAdmin.add(CommandName.SIGN_UP);

        commandSetForAdmin.add(CommandName.ADD_ALBUM);
        commandSetForAdmin.add(CommandName.ADD_ARTIST);
        commandSetForAdmin.add(CommandName.ADD_BONUS);
        commandSetForAdmin.add(CommandName.ADD_GENRE);
        commandSetForAdmin.add(CommandName.ADD_ORDER);
        commandSetForAdmin.add(CommandName.ADD_SONG);
        commandSetForAdmin.add(CommandName.ADD_USER);

        commandSetForAdmin.add(CommandName.SHOW_ALBUM);
        commandSetForAdmin.add(CommandName.SHOW_ARTIST);
        commandSetForAdmin.add(CommandName.SHOW_BONUS);
        commandSetForAdmin.add(CommandName.SHOW_GENRE);
        commandSetForAdmin.add(CommandName.SHOW_ORDER);
        commandSetForAdmin.add(CommandName.SHOW_SONG);
        commandSetForAdmin.add(CommandName.SHOW_USER);
        commandSetForAdmin.add(CommandName.SHOW_GOODS);

        commandSetForAdmin.add(CommandName.EDIT_ALBUM);
        commandSetForAdmin.add(CommandName.EDIT_ARTIST);
        commandSetForAdmin.add(CommandName.EDIT_BONUS);
        commandSetForAdmin.add(CommandName.EDIT_GENRE);
        commandSetForAdmin.add(CommandName.EDIT_ORDER);
        commandSetForAdmin.add(CommandName.EDIT_SONG);
        commandSetForAdmin.add(CommandName.EDIT_USER);

        commandSetForAdmin.add(CommandName.DELETE_ALBUM);
        commandSetForAdmin.add(CommandName.DELETE_ARTIST);
        commandSetForAdmin.add(CommandName.DELETE_GOOD);
        commandSetForAdmin.add(CommandName.DELETE_BONUS);
        commandSetForAdmin.add(CommandName.DELETE_GENRE);
        commandSetForAdmin.add(CommandName.DELETE_ORDER);
        commandSetForAdmin.add(CommandName.DELETE_SONG);
        commandSetForAdmin.add(CommandName.DELETE_USER);

        /**
         * User commands
         * */

        commandSetForUser.add(CommandName.HOME);
        commandSetForUser.add(CommandName.CHANGE_LOCALE);
        commandSetForUser.add(CommandName.SIGN_OUT);
        commandSetForUser.add(CommandName.SIGN_IN);
        commandSetForUser.add(CommandName.SIGN_UP);

        commandSetForUser.add(CommandName.SHOW_ALBUM);
        commandSetForUser.add(CommandName.SHOW_ARTIST);
        commandSetForUser.add(CommandName.SHOW_BONUS);
        commandSetForUser.add(CommandName.SHOW_GENRE);
        commandSetForUser.add(CommandName.SHOW_ORDER);
        commandSetForUser.add(CommandName.SHOW_SONG);
        commandSetForUser.add(CommandName.SHOW_USER);
        commandSetForUser.add(CommandName.SHOW_COMMENT);

        commandSetForUser.add(CommandName.ADD_GOOD);
        commandSetForUser.add(CommandName.BUY);
        commandSetForUser.add(CommandName.DELETE_USER_GOOD);
        commandSetForUser.add(CommandName.SEND_MESSAGE);
        commandSetForUser.add(CommandName.SHOW_USER_GOODS);
        commandSetForUser.add(CommandName.USE_BONUS);
    }

    /**
     * @see Filter#destroy()
     */

    public void destroy() {
        commandSetForUser = null;
        commandSetForUnauthorizedUser = null;
        commandSetForAdmin = null;
    }
}
