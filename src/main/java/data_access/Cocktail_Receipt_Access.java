package data_access;

import java.util.List;

import entity.Cocktail_Receipt;
import api.CocktailsDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

public class Cocktail_Receipt_Access {
    private JSONObject cocktailReceiptsJson;
    private List<Cocktail_Receipt> cocktailReceipts;
    private List<String> categories;
    public Cocktail_Receipt_Access() {
        cocktailReceiptsJson = CocktailsDatabase.
        categories = new CocktailsDatabase().getCategories();
        cocktailReceipts = new CocktailsDatabase().getAllCocktailReceipts();
    }
}
