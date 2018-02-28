package com.epam.mediaserver.command;


import com.epam.mediaserver.command.impl.Buy;
import com.epam.mediaserver.command.impl.HomePage;
import com.epam.mediaserver.command.impl.LocaleChange;
import com.epam.mediaserver.command.impl.SignIn;
import com.epam.mediaserver.command.impl.SignOut;
import com.epam.mediaserver.command.impl.SignUp;
import com.epam.mediaserver.command.impl.UseBonus;
import com.epam.mediaserver.command.impl.add.AlbumAdd;
import com.epam.mediaserver.command.impl.add.ArtistAdd;
import com.epam.mediaserver.command.impl.add.BonusAdd;
import com.epam.mediaserver.command.impl.add.GenreAdd;
import com.epam.mediaserver.command.impl.add.GoodAdd;
import com.epam.mediaserver.command.impl.add.OrderAdd;
import com.epam.mediaserver.command.impl.add.SongAdd;
import com.epam.mediaserver.command.impl.add.UserAdd;
import com.epam.mediaserver.command.impl.check.CheckEmail;
import com.epam.mediaserver.command.impl.check.CheckLogin;
import com.epam.mediaserver.command.impl.delete.AlbumDelete;
import com.epam.mediaserver.command.impl.delete.ArtistDelete;
import com.epam.mediaserver.command.impl.delete.BonusDelete;
import com.epam.mediaserver.command.impl.delete.GenreDelete;
import com.epam.mediaserver.command.impl.delete.GoodDelete;
import com.epam.mediaserver.command.impl.delete.OrderDelete;
import com.epam.mediaserver.command.impl.delete.SongDelete;
import com.epam.mediaserver.command.impl.delete.UserDelete;
import com.epam.mediaserver.command.impl.delete.UserGoodDelete;
import com.epam.mediaserver.command.impl.edit.AlbumEdit;
import com.epam.mediaserver.command.impl.edit.ArtistEdit;
import com.epam.mediaserver.command.impl.edit.BonusEdit;
import com.epam.mediaserver.command.impl.edit.GenreEdit;
import com.epam.mediaserver.command.impl.edit.OrderEdit;
import com.epam.mediaserver.command.impl.edit.SongEdit;
import com.epam.mediaserver.command.impl.edit.UserEdit;
import com.epam.mediaserver.command.impl.show.SendMessage;
import com.epam.mediaserver.command.impl.show.ShowAlbums;
import com.epam.mediaserver.command.impl.show.ShowArtists;
import com.epam.mediaserver.command.impl.show.ShowBonuses;
import com.epam.mediaserver.command.impl.show.ShowComments;
import com.epam.mediaserver.command.impl.show.ShowGenres;
import com.epam.mediaserver.command.impl.show.ShowGoods;
import com.epam.mediaserver.command.impl.show.ShowOrders;
import com.epam.mediaserver.command.impl.show.ShowSongs;
import com.epam.mediaserver.command.impl.show.ShowUserGoods;
import com.epam.mediaserver.command.impl.show.ShowUsers;
import com.epam.mediaserver.constant.CommandName;

import java.util.HashMap;
import java.util.Map;


/**
 * Class for handling commands and command types
 */

public class CommandManager {

    private static final char OLD_CHAR = '-';
    private static final char NEW_CHAR = '_';
    private static CommandManager instance = new CommandManager();
    private Map<CommandName, Command> commands = new HashMap<>();
    private Map<CommandName, Command> adminCommands = new HashMap<>();

    private CommandManager() {

        //Guest commands

        commands.put(CommandName.CHECK_EMAIL, new CheckEmail());
        commands.put(CommandName.CHECK_LOGIN, new CheckLogin());

        commands.put(CommandName.HOME, new HomePage());
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.SIGN_OUT, new SignOut());
        commands.put(CommandName.CHANGE_LOCALE, new LocaleChange());

        //User commands

        commands.put(CommandName.SHOW_COMMENT, new ShowComments());
        commands.put(CommandName.SEND_MESSAGE, new SendMessage());
        commands.put(CommandName.ADD_GOOD, new GoodAdd());
        commands.put(CommandName.SHOW_USER_GOODS, new ShowUserGoods());
        commands.put(CommandName.DELETE_USER_GOOD, new UserGoodDelete());
        commands.put(CommandName.USE_BONUS, new UseBonus());
        commands.put(CommandName.BUY, new Buy());

        //Admin commands (CRUD)

        //Create
        commands.put(CommandName.ADD_ALBUM, new AlbumAdd());
        commands.put(CommandName.ADD_ARTIST, new ArtistAdd());
        commands.put(CommandName.ADD_BONUS, new BonusAdd());
        commands.put(CommandName.ADD_GENRE, new GenreAdd());
        commands.put(CommandName.ADD_ORDER, new OrderAdd());
        commands.put(CommandName.ADD_SONG, new SongAdd());
        commands.put(CommandName.ADD_USER, new UserAdd());

        //Read
        commands.put(CommandName.SHOW_ALBUM, new ShowAlbums());
        commands.put(CommandName.SHOW_ARTIST, new ShowArtists());
        commands.put(CommandName.SHOW_BONUS, new ShowBonuses());

        commands.put(CommandName.SHOW_GENRE, new ShowGenres());
        commands.put(CommandName.SHOW_ORDER, new ShowOrders());
        commands.put(CommandName.SHOW_GOODS, new ShowGoods());
        commands.put(CommandName.SHOW_SONG, new ShowSongs());
        commands.put(CommandName.SHOW_USER, new ShowUsers());

        //Update
        commands.put(CommandName.EDIT_ALBUM, new AlbumEdit());
        commands.put(CommandName.EDIT_ARTIST, new ArtistEdit());
        commands.put(CommandName.EDIT_BONUS, new BonusEdit());
        commands.put(CommandName.EDIT_GENRE, new GenreEdit());
        commands.put(CommandName.EDIT_ORDER, new OrderEdit());
        commands.put(CommandName.EDIT_SONG, new SongEdit());
        commands.put(CommandName.EDIT_USER, new UserEdit());

        //Delete
        commands.put(CommandName.DELETE_ALBUM, new AlbumDelete());
        commands.put(CommandName.DELETE_ARTIST, new ArtistDelete());
        commands.put(CommandName.DELETE_BONUS, new BonusDelete());
        commands.put(CommandName.DELETE_GENRE, new GenreDelete());
        commands.put(CommandName.DELETE_ORDER, new OrderDelete());
        commands.put(CommandName.DELETE_SONG, new SongDelete());
        commands.put(CommandName.DELETE_USER, new UserDelete());
        commands.put(CommandName.DELETE_GOOD, new GoodDelete());
    }//http://localhost:8080/Controller?command=delete-song&id=14

    public static CommandManager getInstance() {
        return instance;
    }

    /**
     * Method which return instance of {@link com.epam.mediaserver.command.Command} from request param
     *
     * @param name a string that contains command name
     * @return instance of {@link com.epam.mediaserver.command.Command} by it's name from
     * {@link CommandName}
     */

    public Command getCommand(String name) {
        name = name.replace(OLD_CHAR, NEW_CHAR);
        CommandName commandName = CommandName.valueOf(name.toUpperCase());

        Command command = commands.get(commandName);
        return command;
    }
}
