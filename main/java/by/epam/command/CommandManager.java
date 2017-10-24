package by.epam.command;

import by.epam.command.impl.*;
import by.epam.command.impl.table.add.*;
import by.epam.command.impl.table.delete.*;
import by.epam.command.impl.table.edit.*;
import by.epam.command.impl.table.show.*;
import by.epam.command.impl.user.*;
import by.epam.constant.CommandName;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling commands and command types
 */

public class CommandManager {

    private static final char OLD_CHAR = '-';
    private static final char NEW_CHAR = '_';

    private Map<CommandName, Command> commands = new HashMap<>();
    private Map<CommandName, Command> adminCommands = new HashMap<>();
    private static CommandManager instance = new CommandManager();

    public static CommandManager getInstance() {
        return instance;
    }

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
        commands.put(CommandName.DELETE_USER_GOOD, new DeleteUserGood());
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



    }

    /**
     * Method which return instance of {@link Command} from request param
     *
     * @param name
     *            a string that contains command name
     * @return instance of {@link Command} by it's name from
     *         {@link CommandName}
     */

    public Command getCommand(String name) {
        name = name.replace(OLD_CHAR, NEW_CHAR);
        CommandName commandName = CommandName.valueOf(name.toUpperCase());

        Command command = commands.get(commandName);
        return command;
    }
}
