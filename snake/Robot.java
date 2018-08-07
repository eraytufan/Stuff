
public class Robot {
	//the point where robot is
	protected Point point = new Point(10,10);
	protected String direction = "N";
	
	//turns left the robot
	public void turnLeft(){
		if(direction.equals("N")){
			direction = "W";
		}
		else if(direction.equals("W")){
			direction = "S";
		}
		else if(direction.equals("S")){
			direction = "E";
		}
		else if(direction.equals("E")){
			direction = "N";
		}
	}
	//turns right the robot
	public void turnRight(){
		
		if(direction.equals("N")){
			direction = "E";
		}
		else if(direction.equals("W")){
			direction = "N";
		}
		else if(direction.equals("S")){
			direction = "W";
		}
		else if(direction.equals("E")){
			direction = "S";
		}
	}
	//move the robot without checking bounderies
	public void move(){
		if(direction.equals("N")){
			point.setyValue(point.getyValue()-1);
		}
		else if(direction.equals("W")){
			point.setxValue(point.getxValue()+1);
		}
		else if(direction.equals("S")){
			point.setyValue(point.getyValue()+1);
		}
		else if(direction.equals("E")){
			point.setxValue(point.getxValue()+1);
		}
	}
	
	public Point getLocation(){
		return point;
	}
	public String getDirection(){
		return direction;
	}
}
