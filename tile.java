package willsGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.*;

public class tile extends JPanel {
			
	private tile[] neighbors;
	public static tile[] allTiles = new tile[64];
	private int location;
	public String terrain;
	private BufferedImage forestImage;
	
	public static void imageLoader() {
		try {
			System.out.println("LOADED IMAGE");
			BufferedImage forestImage = ImageIO.read(new File("C:\\Users\\Will Gleason\\eclipse-workspace\\willsGame\\Game images\\images\\forest.jpg"));
		}catch(IOException e) {
			System.out.println("Image failed to load");
		}
	}
	
	public tile(int spot) {
		super(new BorderLayout());
		location = spot;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		neighbors = new tile[4];
		allTiles[spot] = this;
		if(spot + 1 == allTiles.length) {
			findNeighbors();
		}
	}
	
	// Assigns each tile an array of its neighbors
	
	public static void findNeighbors() {
		imageLoader();
		for(tile t : allTiles) {
			if(t.location - 1 >= 0 && (t.location - 1) % 7 != 0) {
				t.neighbors[0] = allTiles[t.location - 1];
			}
			if((t.location + 1) % 8 != 0) {
				t.neighbors[2] = allTiles[t.location + 1];
			}
			if(t.location - 8 >= 0) {
				t.neighbors[1] = allTiles[t.location - 8];
			}
			if(t.location + 8 <= 63) {
				t.neighbors[3] = allTiles[t.location + 8];
			}
		}
		for(tile t : allTiles) {
			t.generateTerrain();
			t.setImages();
		}
	}
	
	// Give each tile either mountains, forest, grass-land or hills. Random assignment, with chances changing
	// depending on neighbors. 
	private void generateTerrain() {
		String prior;
		Random rand = new Random();
		ArrayList<String> terrainChoices = new ArrayList<String>();
		int choice = rand.nextInt(40);
		for(tile neighbor : neighbors) {
			if(neighbor != null && neighbor.terrain != null) {
				terrainChoices.add(neighbor.terrain);
			}
		}
		if(!terrainChoices.isEmpty()) {
			prior = terrainChoices.get(rand.nextInt(terrainChoices.size()));
			if(prior.equals("Mountain")) {
				if(choice <= 5) {
					terrain = "Forest";
				}
				else if(choice <= 10) {
					terrain = "Grassland";
				}
				else if(choice <= 20) {
					terrain = "Foot Hills";
				}else {
					terrain = "Mountain";
				}
			}else if(prior.equals("Forest")) {
				if(choice <= 5) {
					terrain = "Mountain";
				}
				else if(choice <= 10) {
					terrain = "Grassland";
				}
				else if(choice <= 15) {
					terrain = "Forest Hill";
				}else {
					terrain = "Forest";
				}
			}else if(prior.equals("Grassland")){
				if(choice <= 5) {
					terrain = "Mountain";
				}
				else if(choice <= 10) {
					terrain = "Forest";
				}
				else if(choice <= 20) {
					terrain = "Grassy Hill";
				}else {
					terrain = "Grassland";
				}
			}else {
				if(choice <= 10) {
					terrain = "Forest";
				}
				else if(choice <= 20) {
					terrain = "Grassland";
				}
				else if(choice <= 30) {
					choice = rand.nextInt(3);
					switch (choice) {
					case 0:
						terrain = "Grassy Hill";
						break;
					case 1:
						terrain = "Forest Hill";
						break;
					case 2:
						terrain = "Foot Hills";
						break;
					}
				}else {
					terrain = "Mountain";
				}
			}
		}else {
			if(choice <= 10) {
				terrain = "Forest";
			}
			else if(choice <= 20) {
				terrain = "Grassland";
			}
			else if(choice <= 30) {
				choice = rand.nextInt(3);
				switch (choice) {
				case 0:
					terrain = "Grassy Hill";
					break;
				case 1:
					terrain = "Forest Hill";
					break;
				case 2:
					terrain = "Foot Hills";
					break;
				}
			}else {
				terrain = "Mountain";
			}
		}
	}
	
	@Override
	public String toString() {
		return("(" + (location % 8) + ", " + ((int) location / 8) + ")" + " " + terrain);
	}
	
	private void drawing() {
		repaint();
		revalidate();
	}
	
	public void setImages() {
		System.out.println(forestImage);
		if(terrain.equals("Forest")) {
			ImageIcon forestIc = new ImageIcon(forestImage);
			ImageLabel forest = new ImageLabel("Forest");
			forest.setIcon(forestIc);
			this.add(forest, BorderLayout.CENTER);
		}
	}

}
