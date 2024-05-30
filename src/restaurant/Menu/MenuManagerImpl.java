package restaurant.Menu;

import java.util.LinkedHashSet;
import java.util.Set;

public class MenuManagerImpl implements MenuManager {
    private Set<MenuOptions> menuOptions = new LinkedHashSet<>();

    @Override
    public void addMenuOption(MenuOptions option) {
        menuOptions.add(option);
    }

    @Override
    public void removeMenuOption(MenuOptions option) {
        menuOptions.remove(option);
    }

    @Override
    public Set<MenuOptions> getMenuOptions() {
        return menuOptions;
    }
}
