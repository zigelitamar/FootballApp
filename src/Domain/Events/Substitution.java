package Domain.Events;

import Domain.Users.Player;

public class Substitution extends AGameEvent  implements IEvent {
    public Substitution(double gameMinute, Player playerWhocommit) {
        super(gameMinute, playerWhocommit);
    }
}
