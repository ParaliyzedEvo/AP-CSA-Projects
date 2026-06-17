public class ChevyDriverStudent {
    public static void main(String[] args) {
        // Create objects
        Chevy traxBase = new Chevy();
        Chevy traxLux = new Chevy();

        // Set traxBase mileage to 15
        traxBase.setMileage(15);

        // Set traxLux mileage to 175 with both a luxury and 4WD package
        traxLux.setMileage(175);
        traxLux.setLuxuryPackage(true);
        traxLux.setFourWDPackage(true);

        // Printouts
        System.out.println("*** VEHICLE PRINTOUTS ***");
        System.out.println(traxBase);
        System.out.println("After giving the base car a luxury pkg, 4WD package, \nand updating" +
                " miles to 175, its data prints out as:");
        System.out.println(traxLux);

        // Create two Chevy Corvettes to test compareTo and Equals
        Chevy corvetteClassic = new Chevy(1963, 123500, 13.5, 61500, "Corvette (Classic)",
                "Fire Red", true, false, false);
        Chevy corvetteSport = new Chevy(2020, 1500, 18.5, 58900, "Corvette (Sport)",
                "Fire Red", true, false, true);

        // Display mileage comparison
        System.out.println("\n*** COMPARE BY MILEAGE ***");
        System.out.println("\tClassic Corvette vs. Sport Corvette");
        System.out.print("\tWhich car has the least miles?\t");

        if (corvetteClassic.compareTo(corvetteSport) < 0)
            System.out.println("Classic Corvette has less miles than Sport Corvette");
        else if (corvetteClassic.compareTo(corvetteSport) > 0)
            System.out.println("Sport Corvette has less miles than Classic Corvette");
        else
            System.out.println("Tie, both have " + corvetteClassic.getMileage() + " miles");

        // Display equality
        System.out.println("\n*** CHECK EQUALITY ***");
        System.out.println("\tBase Trax vs. Luxury Trax");
        System.out.print("\tStatus:\t");

        if (traxBase.equals(traxLux))
            System.out.println("Same car");
        else
            System.out.println("Not same car");
    }
}