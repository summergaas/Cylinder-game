/*
 * This is a game that is based on solidifying common decimal to fraction conversions to memory.
 * Appropriate for middle-school aged kids.
 * The premise is baking a cake. There is a recipe that uses common decimals,
 * and the player must select the measuring cups that are equal to the decimals to bake the cake.
 * In this instance, the cylinders are the supply of baking materials, the measuring cups, and the mixing bowl.
 *For futer consideration: I would like to make the program more robust so that the player has more room for error while playing,
 *for example, if the player mispells an ingredient, then I would prefer the game to give the player another chance to enter an ingredient rather than lose the game
 *Another future consideration would be keeping track of the ingredients so that we know that they got the right amount of each ingredient, right now it just measures that the right amound is used generally.
 */

import java.util.Scanner; //From Google search, found many sites saying Scanner was a good way to accept user input. Followed examples on http://www.homeandlearn.co.uk/java/user_input.html while working on this project.
import my.cylinder.CylinderUI; 
import my.cylinder.MeasurementsGUI;

public class CylinderGameEC {
    
    private Cylinder[] cylinders;
    private int count; //number of cylinders saved in the array
    public CylinderUI openingGUI;

    public CylinderGameEC( int maxSize ){
    	cylinders = new Cylinder[maxSize]; //sets the length of the array 
       	count = 0; 
        openingGUI = new CylinderUI();
    } 
    
    // add a new cylinder of the given radius and height to the array
    // 
    // But, if the array is already full then just print "ERROR addCylinder"
	// and don't add a new cylinder
    public void addCylinder(double r, double h){
        if (count < cylinders.length){
            Cylinder ca = new Cylinder (r, h);
            cylinders[count] = ca;
            count +=1; //not sure if this will work, since count is a primitive, in a different method
        }
        else {
            System.out.println("ERROR addCylinder");
        }
    }
    
    // if array contains at least one cylinder delete the last one in the array
    // otherwise do nothing
    public void deleteCylinder(){
        if (count>0){
            cylinders[count-1]=null;
            count -= 1;
        }
    }
		public void fillCupsInOrder(Cylinder fromCylinder) {
            int index = 0;
            while (index < count){
                cylinders[index].pourWaterFrom(fromCylinder);
                double sourceWaterVolume = fromCylinder.getWaterVolume();
                if (sourceWaterVolume>0){
                    index += 1;
                }
                else {
                    index = count;
                }
            }
	}

	/*
	Helper function to check if two doubles are close enough to being equal.
	(In general, it is bad to compare doubles for exact equality with ==)
	*/
	private static void check(double got, double expected) {
		final double EPSILON = 0.00001;
		if (Math.abs(got - expected) >= EPSILON) {
			throw new RuntimeException("failed test: got "+got+ " but expected "+expected);
		}
	}

	/*
	Helper function to run a test case

	cg: the game to run
	source: the cup that will be poured from
	expectedVols: expected volumes of cg's Cylinders after the pour
	expectedWaterVols: expected water volumes of cg's Cylinders after the pour
	expectedSrcVols: expected volume of the source after the pour
	expectedSrcWaterVol: expected water volume of the source after the pour
	*/
	private static void testCase(CylinderGameEC cg, Cylinder source, 
			double[] expectedVols, double[] expectedWaterVols,
			double expectedSrcVol, double expectedSrcWaterVol) {
		source.fillToTop();
		cg.fillCupsInOrder(source);
		for (int i=0; i<cg.count; i++) {
			System.out.println(cg.cylinders[i].toString());
			check(cg.cylinders[i].getVolume(), expectedVols[i]); 
			check(cg.cylinders[i].getWaterVolume(), expectedWaterVols[i]);
		}
		System.out.println("source: "+source.toString());
		check(source.getVolume(), expectedSrcVol);
		check(source.getWaterVolume(), expectedSrcWaterVol);
	}

	public static void main(String[] args) {
            
                        
			Cylinder quarterCupC = new Cylinder(1, 0.25);
                        Cylinder halfCupC = new Cylinder (1, 0.5);
                        Cylinder cupC = new Cylinder (1,1);
                        Cylinder teaspoonC = new Cylinder (1, 0.02);
                        Cylinder flourC = new Cylinder (2,2);
                        Cylinder sugarC = new Cylinder (2,2);
                        Cylinder milkC = new Cylinder (2,2);
                        Cylinder cocoaC = new Cylinder (2,2);
                        Cylinder eggsC = new Cylinder (2,2);
                        Cylinder butterC = new Cylinder (2,2);
                        Cylinder bakingSodaC = new Cylinder (2,2);
                        Cylinder bakingPowderC = new Cylinder (2, 2);
                        Cylinder mixingBowl = new Cylinder (4, 4);
                        Cylinder cakePan = new Cylinder (2, 1.635);
                        
                        flourC.fillToTop();
                        sugarC.fillToTop();
                        milkC.fillToTop();
                        cocoaC.fillToTop();
                        eggsC.fillToTop();
                        butterC.fillToTop();
                        bakingSodaC.fillToTop();
                        bakingPowderC.fillToTop();
                        
                        Cylinder ingredientC = flourC;
                        Cylinder utensilC = quarterCupC;
                                                
                        Scanner user_input = new Scanner ( System.in );
                        String ingredient;
                        ingredient = user_input.next();
                        
                        
                        
                        while (!"pan".equals(ingredient)){
                            if ("flour".equals(ingredient)){
                               ingredientC = flourC;
                            }
                            else if ("sugar".equals(ingredient)){
                                ingredientC = sugarC;
                            }
                            else if ("milk".equals(ingredient)){
                                ingredientC = milkC;
                            }
                            else if ("cocoa".equals(ingredient)){
                                ingredientC = cocoaC;
                            }
                            else if ("butter".equals(ingredient)){
                                ingredientC = butterC;
                            }
                            else if ("eggs".equals(ingredient)){
                                ingredientC = eggsC;
                            }
                            else if ("bakingsoda".equals(ingredient)) {
                                ingredientC = bakingSodaC;
                            }
                            else if ("bakingpowder".equals(ingredient)) {
                                ingredientC = bakingPowderC;
                            }
			 
                            
                            String utensil;
                            System.out.println("Choose a measuring utensil (1/4cup, 1/2cup, cup, teaspoon):");
                            utensil = user_input.next();
                            if ("1/4cup".equals(utensil)){
                               utensilC = quarterCupC;
                            }
                            else if ("1/2cup".equals(utensil)){
                                utensilC = halfCupC;
                            }
                            else if ("cup".equals(utensil)){
                                utensilC = cupC;
                            }
                            else if ("teaspoon".equals(utensil)){
                                utensilC = teaspoonC;
                            }
                            else {
                                System.out.println("We don't have the measuring tool! You've lost the game.");
                            }
                            utensilC.pourWaterFrom(ingredientC);
                            mixingBowl.pourWaterFrom(utensilC);
                        
                            System.out.println("Enter an ingredient or, enter 'pan' if you are finished adding ingredients and are ready to pour everything into the pan.");
                            ingredient = user_input.next();
                        
                        }
                        cakePan.pourWaterFrom(mixingBowl);
                        double cakePanWaterVolume = cakePan.getWaterVolume();
                        double cakePanVolume = cakePan.getVolume();
                        double mixingBowlVolume = mixingBowl.getWaterVolume();
                        if (cakePanVolume > 6.5 && cakePanVolume < 6.6 && mixingBowlVolume == 0){
                            System.out.println("All of your ingredients fit perfectly into the cakePan. You win.");
                        }
                         else{
                            System.out.println("Oops! The volume of ingredients in the mixing bowl didn't match the volume of the pan. You've lost the game.");
                            System.out.println(cakePanWaterVolume);
                            System.out.println(mixingBowlVolume);
                        }			
			

	}
    
}
