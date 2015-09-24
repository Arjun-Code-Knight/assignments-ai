import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Assignment 2 : Question 1
 * 
 * @author Arjun Thimmareddy
 * Unity Id : athimma
 * Student Id : 200105939
 * Email: athimma@ncsu.edu
 */
public final class RouteHelper {
	
	private static RouteHelper routeHelper = new RouteHelper();
	private static HashMap<String,String>roadMap ;
	private HashMap<String,String> city;
	private HashMap<String,Double> distanceMap;
	public static final char COMMA = ',';
	
	public static RouteHelper getInstance()
	{
		return routeHelper;
	}
	
	/**
	 * 
	 * Initialize all the Map related data
	 * 
	 */
	private RouteHelper()
	{
		initializeRoadMap();
		initializeCoordinates();
		initializeDistanceMap();
	}
	
	private void initializeDistanceMap()
	{
		distanceMap = new HashMap<String,Double>();
		distanceMap.put("albanyNY,montreal",new Double(226));
		distanceMap.put("albanyNY,boston",new Double(166));
		distanceMap.put("albanyNY,rochester",new Double(148));
		distanceMap.put("albanyGA,tallahassee",new Double(120));
		distanceMap.put("albanyGA,macon",new Double(106));
		distanceMap.put("albuquerque,elPaso",new Double(267));
		distanceMap.put("albuquerque,santaFe",new Double(61));
		distanceMap.put("atlanta,macon",new Double(82));
		distanceMap.put("atlanta,chattanooga",new Double(117));
		distanceMap.put("augusta,charlotte",new Double(161));
		distanceMap.put("augusta,savannah",new Double(131));
		distanceMap.put("austin,houston",new Double(186));
		distanceMap.put("austin,sanAntonio",new Double(79));
		distanceMap.put("bakersfield,losAngeles",new Double(112));
		distanceMap.put("bakersfield,fresno",new Double(107));
		distanceMap.put("baltimore,philadelphia",new Double(102));
		distanceMap.put("baltimore,washington",new Double(45));
		distanceMap.put("batonRouge,lafayette",new Double(50));
		distanceMap.put("batonRouge,newOrleans",new Double(80));
		distanceMap.put("beaumont,houston",new Double(69));
		distanceMap.put("beaumont,lafayette",new Double(122));
		distanceMap.put("boise,saltLakeCity",new Double(349));
		distanceMap.put("boise,portland",new Double(428));
		distanceMap.put("boston,providence",new Double(51));
		distanceMap.put("buffalo,toronto",new Double(105));
		distanceMap.put("buffalo,rochester",new Double(64));
		distanceMap.put("buffalo,cleveland",new Double(191));
		distanceMap.put("calgary,vancouver",new Double(605));
		distanceMap.put("calgary,winnipeg",new Double(829));
		distanceMap.put("charlotte,greensboro",new Double(91));
		distanceMap.put("chattanooga,nashville",new Double(129));
		distanceMap.put("chicago,milwaukee",new Double(90));
		distanceMap.put("chicago,midland",new Double(279));
		distanceMap.put("cincinnati,indianapolis",new Double(110));
		distanceMap.put("cincinnati,dayton",new Double(56));
		distanceMap.put("cleveland,pittsburgh",new Double(157));
		distanceMap.put("cleveland,columbus",new Double(142));
		distanceMap.put("coloradoSprings,denver",new Double(70));
		distanceMap.put("coloradoSprings,santaFe",new Double(316));
		distanceMap.put("columbus,dayton",new Double(72));
		distanceMap.put("dallas,denver",new Double(792));
		distanceMap.put("dallas,mexia",new Double(83));
		distanceMap.put("daytonaBeach,jacksonville",new Double(92));
		distanceMap.put("daytonaBeach,orlando",new Double(54));
		distanceMap.put("denver,wichita",new Double(523));
		distanceMap.put("denver,grandJunction",new Double(246));
		distanceMap.put("desMoines,omaha",new Double(135));
		distanceMap.put("desMoines,minneapolis",new Double(246));
		distanceMap.put("elPaso,sanAntonio",new Double(580));
		distanceMap.put("elPaso,tucson",new Double(320));
		distanceMap.put("eugene,salem",new Double(63));
		distanceMap.put("eugene,medford",new Double(165));
		distanceMap.put("europe,philadelphia",new Double(3939));
		distanceMap.put("ftWorth,oklahomaCity",new Double(209));
		distanceMap.put("fresno,modesto",new Double(109));
		distanceMap.put("grandJunction,provo",new Double(220));
		distanceMap.put("greenBay,minneapolis",new Double(304));
		distanceMap.put("greenBay,milwaukee",new Double(117));
		distanceMap.put("greensboro,raleigh",new Double(74));
		distanceMap.put("houston,mexia",new Double(165));
		distanceMap.put("indianapolis,stLouis",new Double(246));
		distanceMap.put("jacksonville,savannah",new Double(140));
		distanceMap.put("jacksonville,lakeCity",new Double(113));
		distanceMap.put("japan,pointReyes",new Double(5131));
		distanceMap.put("japan,sanLuisObispo",new Double(5451));
		distanceMap.put("kansasCity,tulsa",new Double(249));
		distanceMap.put("kansasCity,stLouis",new Double(256));
		distanceMap.put("kansasCity,wichita",new Double(190));
		distanceMap.put("keyWest,tampa",new Double(446));
		distanceMap.put("lakeCity,tampa",new Double(169));
		distanceMap.put("lakeCity,tallahassee",new Double(104));
		distanceMap.put("laredo,sanAntonio",new Double(154));
		distanceMap.put("laredo,mexico",new Double(741));
		distanceMap.put("lasVegas,losAngeles",new Double(275));
		distanceMap.put("lasVegas,saltLakeCity",new Double(486));
		distanceMap.put("lincoln,wichita",new Double(277));
		distanceMap.put("lincoln,omaha",new Double(58));
		distanceMap.put("littleRock,memphis",new Double(137));
		distanceMap.put("littleRock,tulsa",new Double(276));
		distanceMap.put("losAngeles,sanDiego",new Double(124));
		distanceMap.put("losAngeles,sanLuisObispo",new Double(182));
		distanceMap.put("medford,redding",new Double(150));
		distanceMap.put("memphis,nashville",new Double(210));
		distanceMap.put("miami,westPalmBeach",new Double(67));
		distanceMap.put("midland,toledo",new Double(82));
		distanceMap.put("minneapolis,winnipeg",new Double(463));
		distanceMap.put("modesto,stockton",new Double(29));
		distanceMap.put("montreal,ottawa",new Double(132));
		distanceMap.put("newHaven,providence",new Double(110));
		distanceMap.put("newHaven,stamford",new Double(92));
		distanceMap.put("newOrleans,pensacola",new Double(268));
		distanceMap.put("newYork,philadelphia",new Double(101));
		distanceMap.put("norfolk,richmond",new Double(92));
		distanceMap.put("norfolk,raleigh",new Double(174));
		distanceMap.put("oakland,sanFrancisco",new Double(8));
		distanceMap.put("oakland,sanJose",new Double(42));
		distanceMap.put("oklahomaCity,tulsa",new Double(105));
		distanceMap.put("orlando,westPalmBeach",new Double(168));
		distanceMap.put("orlando,tampa",new Double(84));
		distanceMap.put("ottawa,toronto",new Double(269));
		distanceMap.put("pensacola,tallahassee",new Double(120));
		distanceMap.put("philadelphia,pittsburgh",new Double(319));
		distanceMap.put("philadelphia,newYork",new Double(101));
		distanceMap.put("philadelphia,uk1",new Double(3548));
		distanceMap.put("philadelphia,uk2",new Double(3548));
		distanceMap.put("phoenix,tucson",new Double(117));
		distanceMap.put("phoenix,yuma",new Double(178));
		distanceMap.put("pointReyes,redding",new Double(215));
		distanceMap.put("pointReyes,sacramento",new Double(115));
		distanceMap.put("portland,seattle",new Double(174));
		distanceMap.put("portland,salem",new Double(47));
		distanceMap.put("reno,saltLakeCity",new Double(520));
		distanceMap.put("reno,sacramento",new Double(133));
		distanceMap.put("richmond,washington",new Double(105));
		distanceMap.put("sacramento,sanFrancisco",new Double(95));
		distanceMap.put("sacramento,stockton",new Double(51));
		distanceMap.put("salinas,sanJose",new Double(31));
		distanceMap.put("salinas,sanLuisObispo",new Double(137));
		distanceMap.put("sanDiego,yuma",new Double(172));
		distanceMap.put("saultSteMarie,thunderBay",new Double(442));
		distanceMap.put("saultSteMarie,toronto",new Double(436));
		distanceMap.put("seattle,vancouver",new Double(115));
		distanceMap.put("thunderBay,winnipeg",new Double(440));
	}
	
	private void initializeCoordinates()
	{
		city = new HashMap<String,String>();;
		city.put("albanyGA","31.58,84.17");
		city.put("albanyNY","42.66,73.78");
		city.put("albuquerque","35.11,106.61");
		city.put("atlanta","33.76,84.40");
		city.put("augusta","33.43,82.02");
		city.put("austin","30.30,97.75");
		city.put("bakersfield","35.36,119.03");
		city.put("baltimore","39.31,76.62");
		city.put("batonRouge","30.46,91.14");
		city.put("beaumont","30.08,94.13");
		city.put("boise","43.61,116.24");
		city.put("boston","42.32,71.09");
		city.put("buffalo","42.90,78.85");
		city.put("calgary","51.00,114.00");
		city.put("charlotte","35.21,80.83");
		city.put("chattanooga","35.05,85.27");
		city.put("chicago","41.84,87.68");
		city.put("cincinnati","39.14,84.50");
		city.put("cleveland","41.48,81.67");
		city.put("coloradoSprings","38.86,104.79");
		city.put("columbus","39.99,82.99");
		city.put("dallas","32.80,96.79");
		city.put("dayton","39.76,84.20");
		city.put("daytonaBeach","29.21,81.04");
		city.put("denver","39.73,104.97");
		city.put("desMoines","41.59,93.62");
		city.put("elPaso","31.79,106.42");
		city.put("eugene","44.06,123.11");
		city.put("europe","48.87,-2.33");
		city.put("ftWorth","32.74,97.33");
		city.put("fresno","36.78,119.79");
		city.put("grandJunction","39.08,108.56");
		city.put("greenBay","44.51,88.02");
		city.put("greensboro","36.08,79.82");
		city.put("houston","29.76,95.38");
		city.put("indianapolis","39.79,86.15");
		city.put("jacksonville","30.32,81.66");
		city.put("japan","35.68,220.23");
		city.put("kansascity","39.08,94.56");
		city.put("keyWest","24.56,81.78");
		city.put("lafayette","30.21,92.03");
		city.put("lakeCity","30.19,82.64");
		city.put("laredo","27.52,99.49");
		city.put("lasVegas","36.19,115.22");
		city.put("lincoln","40.81,96.68");
		city.put("littleRock","34.74,92.33");
		city.put("losAngeles","34.03,118.17");
		city.put("macon","32.83,83.65");
		city.put("medford","42.33,122.86");
		city.put("memphis","35.12,89.97");
		city.put("mexia","31.68,96.48");
		city.put("mexico","19.40,99.12");
		city.put("miami","25.79,80.22");
		city.put("midland","43.62,84.23");
		city.put("milwaukee","43.05,87.96");
		city.put("minneapolis","44.96,93.27");
		city.put("modesto","37.66,120.99");
		city.put("montreal","45.50,73.67");
		city.put("nashville","36.15,86.76");
		city.put("newHaven","41.31,72.92");
		city.put("newOrleans","29.97,90.06");
		city.put("newYork","40.70,73.92");
		city.put("norfolk","36.89,76.26");
		city.put("oakland","37.80,122.23");
		city.put("oklahomaCity","35.48,97.53");
		city.put("omaha","41.26,96.01");
		city.put("orlando","28.53,81.38");
		city.put("ottawa","45.42,75.69");
		city.put("pensacola","30.44,87.21");
		city.put("philadelphia","40.72,76.12");
		city.put("phoenix","33.53,112.08");
		city.put("pittsburgh","40.40,79.84");
		city.put("pointReyes","38.07,122.81");
		city.put("portland","45.52,122.64");
		city.put("providence","41.80,71.36");
		city.put("provo","40.24,111.66");
		city.put("raleigh","35.82,78.64");
		city.put("redding","40.58,122.37");
		city.put("reno","39.53,119.82");
		city.put("richmond","37.54,77.46");
		city.put("rochester","43.17,77.61");
		city.put("sacramento","38.56,121.47");
		city.put("salem","44.93,123.03");
		city.put("salinas","36.68,121.64");
		city.put("saltLakeCity","40.75,111.89");
		city.put("sanAntonio","29.45,98.51");
		city.put("sanDiego","32.78,117.15");
		city.put("sanFrancisco","37.76,122.44");
		city.put("sanJose","37.30,121.87");
		city.put("sanLuisObispo","35.27,120.66");
		city.put("santaFe","35.67,105.96");
		city.put("saultSteMarie","46.49,84.35");
		city.put("savannah","32.05,81.10");
		city.put("seattle","47.63,122.33");
		city.put("stLouis","38.63,90.24");
		city.put("stamford","41.07,73.54");
		city.put("stockton","37.98,121.30");
		city.put("tallahassee","30.45,84.27");
		city.put("tampa","27.97,82.46");
		city.put("thunderBay","48.38,89.25");
		city.put("toledo","41.67,83.58");
		city.put("toronto","43.65,79.38");
		city.put("tucson","32.21,110.92");
		city.put("tulsa","36.13,95.94");
		city.put("uk1","51.30,0.00");
		city.put("uk2","51.30,0.00");
		city.put("vancouver","49.25,123.10");
		city.put("washington","38.91,77.01");
		city.put("westPalmBeach","26.71,80.05");
		city.put("wichita","37.69,97.34");
		city.put("winnipeg","49.90,97.13");
		city.put("yuma","32.69,114.62");
	}
	
	private void initializeRoadMap()
	{
		roadMap = new HashMap<String,String>();
		roadMap.put("albanyGA","macon,tallahassee");
		roadMap.put("albanyNY","boston,montreal,rochester");
		roadMap.put("albuquerque","elPaso,santaFe");
		roadMap.put("atlanta","chattanooga,macon");
		roadMap.put("augusta","charlotte,savannah");
		roadMap.put("austin","houston,sanAntonio");
		roadMap.put("bakersfield","fresno,losAngeles");
		roadMap.put("baltimore","philadelphia,washington");
		roadMap.put("batonRouge","lafayette,newOrleans");
		roadMap.put("beaumont","houston,lafayette");
		roadMap.put("boise","portland,saltLakeCity");
		roadMap.put("boston","albanyNY,providence");
		roadMap.put("buffalo","cleveland,rochester,toronto");
		roadMap.put("calgary","vancouver,winnipeg");
		roadMap.put("charlotte","augusta,greensboro");
		roadMap.put("chattanooga","atlanta,nashville");
		roadMap.put("chicago","midland,milwaukee");
		roadMap.put("cincinnati","dayton,indianapolis");
		roadMap.put("cleveland","buffalo,columbus,pittsburgh");
		roadMap.put("coloradoSprings","denver,santaFe");
		roadMap.put("columbus","cleveland,dayton");
		roadMap.put("dallas","denver,mexia");
		roadMap.put("dayton","cincinnati,columbus");
		roadMap.put("daytonaBeach","jacksonville,orlando");
		roadMap.put("denver","coloradoSprings,dallas,grandJunction,wichita");
		roadMap.put("desMoines","minneapolis,omaha");
		roadMap.put("elPaso","albuquerque,sanAntonio,tucson");
		roadMap.put("eugene","medford,salem");
		roadMap.put("europe","philadelphia");
		roadMap.put("fresno","bakersfield,modesto");
		roadMap.put("ftWorth","oklahomaCity");
		roadMap.put("grandJunction","denver,provo");
		roadMap.put("greenBay","milwaukee,minneapolis");
		roadMap.put("greensboro","charlotte,raleigh");
		roadMap.put("houston","austin,beaumont,mexia");
		roadMap.put("indianapolis","cincinnati,stLouis");
		roadMap.put("jacksonville","daytonaBeach,lakeCity,savannah");
		roadMap.put("japan","pointReyes,sanLuisObispo");
		roadMap.put("kansasCity","stLouis,tulsa,wichita");
		roadMap.put("keyWest","tampa");
		roadMap.put("lafayette","batonRouge,beaumont");
		roadMap.put("lakeCity","jacksonville,tallahassee,tampa");
		roadMap.put("laredo","mexico,sanAntonio");
		roadMap.put("lasVegas","losAngeles,saltLakeCity");
		roadMap.put("lincoln","omaha,wichita");
		roadMap.put("littleRock","memphis,tulsa");
		roadMap.put("losAngeles","bakersfield,lasVegas,sanDiego,sanLuisObispo");
		roadMap.put("macon","albanyGA,atlanta");
		roadMap.put("medford","eugene,redding");
		roadMap.put("memphis","littleRock,nashville");
		roadMap.put("mexia","dallas,houston");
		roadMap.put("mexico","laredo");
		roadMap.put("miami","westPalmBeach");
		roadMap.put("midland","chicago,toledo");
		roadMap.put("milwaukee","chicago,greenBay");
		roadMap.put("minneapolis","desMoines,greenBay,winnipeg");
		roadMap.put("modesto","fresno,stockton");
		roadMap.put("montreal","albanyNY,ottawa");
		roadMap.put("nashville","chattanooga,memphis");
		roadMap.put("newHaven","providence,stamford");
		roadMap.put("newOrleans","batonRouge,pensacola");
		roadMap.put("newYork","philadelphia");
		roadMap.put("norfolk","raleigh,richmond");
		roadMap.put("oakland","sanFrancisco,sanJose");
		roadMap.put("oklahomaCity","ftWorth,tulsa");
		roadMap.put("omaha","desMoines,lincoln");
		roadMap.put("orlando","daytonaBeach,tampa,westPalmBeach");
		roadMap.put("ottawa","montreal,toronto");
		roadMap.put("pensacola","newOrleans,tallahassee");
		roadMap.put("philadelphia","baltimore,europe,newYork,pittsburgh,uk1,uk2");
		roadMap.put("phoenix","tucson,yuma");
		roadMap.put("pittsburgh","cleveland,philadelphia");
		roadMap.put("pointReyes","japan,redding,sacramento");
		roadMap.put("portland","boise,salem,seattle");
		roadMap.put("providence","boston,newHaven");
		roadMap.put("provo","grandJunction");
		roadMap.put("raleigh","greensboro,norfolk");
		roadMap.put("redding","medford,pointReyes");
		roadMap.put("reno","sacramento,saltLakeCity");
		roadMap.put("richmond","norfolk,washington");
		roadMap.put("rochester","albanyNY,buffalo");
		roadMap.put("sacramento","pointReyes,reno,sanFrancisco,stockton");
		roadMap.put("salem","eugene,portland");
		roadMap.put("salinas","sanJose,sanLuisObispo");
		roadMap.put("saltLakeCity","boise,lasVegas,reno");
		roadMap.put("sanAntonio","austin,elPaso,laredo");
		roadMap.put("sanDiego","losAngeles,yuma");
		roadMap.put("sanFrancisco","oakland,sacramento");
		roadMap.put("sanJose","oakland,salinas");
		roadMap.put("sanLuisObispo","japan,losAngeles,salinas");
		roadMap.put("santaFe","albuquerque,coloradoSprings");
		roadMap.put("saultSteMarie","thunderBay,toronto");
		roadMap.put("savannah","augusta,jacksonville");
		roadMap.put("seattle","portland,vancouver");
		roadMap.put("stLouis","indianapolis,kansasCity");
		roadMap.put("stamford","newHaven");
		roadMap.put("stockton","modesto,sacramento");
		roadMap.put("tallahassee","albanyGA,lakeCity,pensacola");
		roadMap.put("tampa","keyWest,lakeCity,orlando");
		roadMap.put("thunderBay","saultSteMarie,winnipeg");
		roadMap.put("toledo","midland");
		roadMap.put("toronto","buffalo,ottawa,saultSteMarie");
		roadMap.put("tucson","elPaso,phoenix");
		roadMap.put("tulsa","kansasCity,littleRock,oklahomaCity");
		roadMap.put("uk1","philadelphia");
		roadMap.put("uk2","philadelphia");
		roadMap.put("vancouver","calgary,seattle");
		roadMap.put("washington","baltimore,richmond");
		roadMap.put("westPalmBeach","miami,orlando");
		roadMap.put("wichita","denver,kansasCity,lincoln");
		roadMap.put("winnipeg","calgary,minneapolis,thunderBay");
		roadMap.put("yuma","phoenix,sanDiego");
	}
	
	public double getHeuristicEstimate(String sourceCity, String destinationCity)
	{
		String srcCityLatLong = city.get(sourceCity);
		String srcLat = srcCityLatLong.substring(0,srcCityLatLong.indexOf(','));
		Double numSrcLat = Double.parseDouble(srcLat);
		String srcLong = srcCityLatLong.substring(srcCityLatLong.indexOf(',')+1);
		Double numSrcLong = Double.parseDouble(srcLong);
		
		String destCityLatLong = city.get(destinationCity);
		String destLat = destCityLatLong.substring(0,destCityLatLong.indexOf(','));
		Double numdestLat = Double.parseDouble(destLat);
		String destLong = destCityLatLong.substring(destCityLatLong.indexOf(',')+1);
		Double numdestLong = Double.parseDouble(destLong);
		
		double heuristicEstimate = Math.sqrt(Math.pow((69.5 * (numSrcLat - numdestLat)), 2) + Math.pow(69.5
				* Math.cos((numSrcLat + numdestLat) / 360 * Math.PI) * (numSrcLong - numdestLong), 2));
		return heuristicEstimate;
	}

	/*
	 * Get PathCost
	 */
	public double getPathCost(String currentNode, String nextNode)
	{
		String concatStr = currentNode+COMMA+nextNode;
		double distance;
		if(distanceMap.keySet().contains(concatStr))
		{
			distance = distanceMap.get(concatStr);
		}else
		{
			concatStr = nextNode+COMMA+currentNode;
			distance = distanceMap.get(concatStr);
		}
		return distance;
	}
	
	/**
	 * 
	 * get Possible successor Nodes
	 */
	public Set<String> getPossibleSucessors(String lastNode)
	{
		Set<String> succNodes = new HashSet<String>(); 
		String strpossSuccNodes = roadMap.get(lastNode);
		succNodes = splitIntoComponents(strpossSuccNodes);
		return succNodes;
	}
	
	
	/*
	 * Split string into components
	 * 
	 */
	public Set<String> splitIntoComponents(String originalString)
	{
		int startidx = 0;
		int tmpIdx = originalString.indexOf(COMMA);
		Set<String> retList = new HashSet<String>();
		while(tmpIdx != -1)
		{
			retList.add(originalString.substring(startidx,tmpIdx));
			startidx = tmpIdx+1;
			tmpIdx = originalString.indexOf(COMMA,startidx);
			if(tmpIdx == -1 && startidx <= (originalString.length()-1))
				retList.add(originalString.substring(startidx));
		}
		return retList;
	}
	
}
