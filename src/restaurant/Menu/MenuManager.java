package restaurant.Menu;

import java.util.Set;

public interface MenuManager {
    void addMenuOption(MenuOptions option);
    void removeMenuOption(MenuOptions option);
    Set<MenuOptions> getMenuOptions();
}
