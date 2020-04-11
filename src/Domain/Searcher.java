package Domain;

import Domain.SeasonManagment.Leaugue;
import Domain.SeasonManagment.Season;
import Domain.SeasonManagment.Team;
import Domain.Users.Player;
import Domain.Users.TeamManager;
import Domain.Users.TeamOwner;

import javax.management.relation.Role;
import java.util.HashSet;

public abstract class Searcher {

    public HashSet<Object> search(String str){
        return null;
    }

    public HashSet<Player> PlayerFilter(HashSet<Object> searchResults){
        HashSet<Player> PlayerSearchResults = new HashSet<>();
        for (Object o : searchResults){
            if (o instanceof Player){
                PlayerSearchResults.add((Player)o);
            }
        }
        return PlayerSearchResults;
    }

    public HashSet<TeamOwner> TeamOwnerFilter(HashSet<Object> searchResults){
        HashSet<TeamOwner> TeamOwnerSearchResults = new HashSet<>();
        for (Object o : searchResults){
            if (o instanceof TeamOwner){
                TeamOwnerSearchResults.add((TeamOwner)o);
            }
        }
        return TeamOwnerSearchResults;
    }

    public HashSet<TeamManager> TeamManagerFilter(HashSet<Object> searchResults){
        HashSet<TeamManager> TeamManagerSearchResults = new HashSet<>();
        for (Object o : searchResults){
            if (o instanceof TeamManager){
                TeamManagerSearchResults.add((TeamManager)o);
            }
        }
        return TeamManagerSearchResults;
    }

    public HashSet<Leaugue> leaugueFilter(HashSet<Object> searchResults){
        HashSet<Leaugue> leauguesSearchResults = new HashSet<>();
        for (Object o : searchResults){
            if (o instanceof Leaugue){
                leauguesSearchResults.add((Leaugue)o);
            }
        }
        return leauguesSearchResults;
    }

    public HashSet<Team> teamFilter(HashSet<Object> searchResults){
        HashSet<Team> teamsSearchResults = new HashSet<>();
        for (Object o : searchResults){
            if (o instanceof Team){
                teamsSearchResults.add((Team)o);
            }
        }
        return teamsSearchResults;
    }

    public HashSet<Season> seasonFilter(HashSet<Object> searchResults){
        HashSet<Season> seasonsSearchResults = new HashSet<>();
        for (Object o : searchResults){
            if (o instanceof Season){
                seasonsSearchResults.add((Season) o);
            }
        }
        return seasonsSearchResults;
    }
}
