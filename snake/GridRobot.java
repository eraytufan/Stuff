
public class GridRobot extends Robot{
	
	String name; //the name of the robot
	private int gridWidth;
	private int gridHeight;
	
	public int getGridWidth() {
		return gridWidth;
	}
	public int getGridHeight() {
		return gridHeight;
	}
	//constructor
	public GridRobot(String name1, int height, int width){
		this.name = name1;
		this.gridHeight = height;
		this.gridWidth = width;
	}
	//override the robot's move method
	//now checks if it is out of bonds and prints to console
	public void move(){
		if(direction.equals("N")){
			if(point.getyValue() == 0){
				System.out.println("You cannot go to north");
				return;
			}
			point.setyValue(point.getyValue()-1);
		}
		else if(direction.equals("W")){
			if(point.getxValue() == 0){
				System.out.println("You cannot go to west");
				return;
			}
			point.setxValue(point.getxValue()-1);
		}
		else if(direction.equals("S")){
			if(point.getyValue() == (gridHeight-1)){
				System.out.println("You cannot go to south");
				return;
			}
			point.setyValue(point.getyValue()+1);
		}
		else if(direction.equals("E")){
			if(point.getxValue() == (gridWidth-1)){
				System.out.println("You cannot go to east");
				return;
			}
			point.setxValue(point.getxValue()+1);
		}
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	
	
	
}
