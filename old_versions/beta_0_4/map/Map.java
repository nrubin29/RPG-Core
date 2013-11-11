package me.nrubin29.rpg.map;

import java.util.ArrayList;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.audio.Music;
import me.nrubin29.rpg.tile.Row;
import me.nrubin29.rpg.tile.Tile;

public abstract class Map {

	public static final String EMPTY_ROW = "E E E E E E E E E E E E E E E E E E E E";
	
	private Maps mapsEnumConst;
	private MapType type;
	private String name;
	private Music backgroundMusic;
	private ArrayList<Row> rows;
	
	public Map(Maps mapsEnumConst, MapType type, String name, Music backgroundMusic, String... stringRows) {
		this.mapsEnumConst = mapsEnumConst;
		this.type = type;
		this.name = name;
		this.backgroundMusic = backgroundMusic;
		this.rows = new ArrayList<Row>();
		
		for (String row : stringRows) {
			Row r = new Row();
			
			for (String str : row.split(" ")) {
				str.trim();
				if (!str.equals("")) {
					r.addTile(Tile.byID(str));
				}
			}
			
			rows.add(r);
		}
	}
	
	public final Maps getMapsEnumConstant() {
		return mapsEnumConst;
	}
	
	public final MapType getType() {
		return type;
	}
	
	public final String getName() {
		return name;
	}
	
	public final Music getBackgroundMusic() {
		return backgroundMusic;
	}
	
	public final Row getRow(int row) {
		return rows.get(row);
	}
	
	public final void display() {
		Main.getGUI().renderMap(this);
	}
}