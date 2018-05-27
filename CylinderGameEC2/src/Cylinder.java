

public class Cylinder {
   private final double radius;    // doesn't change after Cylinder is constructed   
   private final double height; // doesn't change after Cylinder is constructed

   private double waterHeight;  
    
   /*
     * If any value is less than or equal to 0, 
     * set radius and height to 1 and print "ERROR Cylinder"
     */
    public Cylinder(double radius, double height){
       waterHeight = 0;           
       if (radius<=0){
           System.out.println("ERROR Cylinder");   
           this.radius = 1;
         }
       else {
           this.radius=radius;
       }
       if (height<=0){
           System.out.println("Error Cylinder");
           this.height = 1;
       }
       else {
           this.height=height;
       }
    } 
   
    
    public Cylinder(){ 
        radius = 1;
        height = 1;
        waterHeight =0;  
    }
    
    public double getRadius(){
        return this.radius;
    }

    public double getHeight(){
        return this.height;
    }
    
    public double getVolume(){
        double volume = (this.radius * this.radius * this.height);
        return volume;
    }
    
    public double getWaterVolume(){
        double waterVolume = (this.radius * this.radius * this.waterHeight);
        return waterVolume;
    }
    
    
	// toString: returns a String describing the cylinder
	//   for example, for the Cylinder with radius=10, height=12, waterHeight=4
	//   the String will be "Cylinder(volume=1200pi, waterVolume=400pi)",
	// that is the total volume and current water volume
    public String toString() {
		final String pi = "\u03c0";
        return "Cylinder(volume=" + getVolume() + pi + ", waterVolume=" + getWaterVolume() + pi + ")"; 
        
    }

    // pourWaterFrom
	// Move water from other into this Cylinder.
	// If you would overflow this Cylinder, *only* move the volume
	// of water that would fill this Cylinder to the top.
    public void pourWaterFrom(Cylinder other){
        double thisVolumeCapacity = this.getVolume();
        double thisWaterVolume = this.getWaterVolume();
        double spaceAvailable = (thisVolumeCapacity - thisWaterVolume);
        double thisHeight = this.getHeight();
        double thisRadius = this.getRadius();
        double otherWaterVolume = other.getWaterVolume();
        double otherHeight = other.getHeight();
        double otherRadius = other.getRadius();
        double heightAdded = 0;
        double otherRemainingWaterVolume = otherWaterVolume;
        double otherRemainingWaterHeight = otherHeight;
        if (spaceAvailable > otherWaterVolume){ //if there is enough space to add all the water from the other container
            heightAdded = (otherWaterVolume/(this.radius * this.radius)); //have to add by height, not volume, so figure out how much height will be added
            this.waterHeight += heightAdded; //add the height of the water we added
            other.waterHeight = 0; //Since we added all the water from other, it doesn't have any water in it anymore
        }
        else {
            this.fillToTop();
            otherRemainingWaterVolume = (otherWaterVolume-spaceAvailable); //figure out how much water volume is left in the other container after using all the avaiable space in this container
            otherRemainingWaterHeight = (otherRemainingWaterVolume/(otherRadius * otherRadius));  //conver volume information to height information
            other.waterHeight = otherRemainingWaterHeight; //update other container's new water height
        }
    }

	// completely fills this Cylinder with water
	public void fillToTop() {
		this.waterHeight = this.height;	
	}
}
