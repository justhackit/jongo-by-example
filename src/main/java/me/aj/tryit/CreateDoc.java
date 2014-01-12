package me.aj.tryit;

import java.net.UnknownHostException;
import java.util.Set;

import org.ingini.mongodb.jongo.example.domain.characters.Address;
import org.ingini.mongodb.jongo.example.domain.characters.Hero;
import org.ingini.mongodb.jongo.example.domain.characters.Heroine;
import org.ingini.mongodb.jongo.example.domain.characters.HumanCharacter;
import org.ingini.mongodb.jongo.example.domain.characters.Region;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.google.common.collect.Sets;

public class CreateDoc {
	Jongo jongo;
	public CreateDoc() throws UnknownHostException{
		jongo=new Jongo(ConnectToMongo.getDBInstance());
		this.createHeroine();
		this.createHero();
	}
	public void createHeroine() throws UnknownHostException{
		
		MongoCollection heroine=jongo.getCollection("heroine");
		Heroine aryaStark = (Heroine) Heroine.createHeroineWithoutChildrenNoBeastsAndNoWeapon("Arya", "Stark", 
                new Address("Winterfell", "Westeros", Region.THE_NORTH));
		heroine.insert(aryaStark);
		System.out.println("A Heroine created");
	}
	public void createHero(){
		MongoCollection heroes = jongo.getCollection("heroes");

		Address castleWinterfell = new Address("Winterfell", "Westeros", Region.THE_NORTH);

		Set<HumanCharacter> children = Sets.newHashSet();
		children.add(Hero.createHeroWithoutChildrenNoBeastsAndNoWeapon("Robb", "Stark", castleWinterfell));
		children.add(Heroine.createHeroineWithoutChildrenNoBeastsAndNoWeapon("Sansa", "Stark", castleWinterfell));
		children.add(Heroine.createHeroineWithoutChildrenNoBeastsAndNoWeapon("Arya", "Stark", castleWinterfell));
		children.add(Hero.createHeroWithoutChildrenNoBeastsAndNoWeapon("Bran", "Stark", castleWinterfell));
		children.add(Hero.createHeroWithoutChildrenNoBeastsAndNoWeapon("Rickon", "Stark", castleWinterfell));
		children.add(Hero.createHeroWithoutChildrenNoBeastsAndNoWeapon("Jon", "Snow", castleWinterfell));

		Hero eddardStark = new Hero(null,"Eddard", "Stark",  castleWinterfell, children, null, null);

		heroes.insert(eddardStark);
	}
	public static void main(String args[]) throws UnknownHostException{
		new CreateDoc();
	}

}
