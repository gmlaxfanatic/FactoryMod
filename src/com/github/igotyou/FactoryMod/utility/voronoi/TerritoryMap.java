package com.github.igotyou.FactoryMod.utility.voronoi;

import com.github.igotyou.FactoryMod.interfaces.Factory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TerritoryMap {

	private Map<Factory, Double> areas;
	private static double totalArea=30000*30000;
	
	public TerritoryMap(Set<Factory> factories) {
		areas = new HashMap<Factory, Double>();
		Voronoi v = new Voronoi(1);
		Factory[] sitenbrs = new Factory[factories.size()];
		int i = 0;
		double[] latValues = new double[factories.size()];
		double[] lngValues = new double[factories.size()];
		for (Factory factory : factories) {
			sitenbrs[i] = factory;
			latValues[i] = factory.getLocation().getBlockX();
			lngValues[i] = factory.getLocation().getBlockY();
		}
		List<GraphEdge> graphEdges = v.generateVoronoi(latValues, lngValues, -15000, 15000, -15000, 15000);
		for (GraphEdge graphEdge : graphEdges) {
			updateArea(sitenbrs[graphEdge.site1], graphEdge);
			updateArea(sitenbrs[graphEdge.site2], graphEdge);

		}
	}

	private void updateArea(Factory factory, GraphEdge graphEdge) {
		if (!areas.containsKey(factory)) {
			areas.put(factory, 0d);
		}
		areas.put(factory, areas.get(factory).doubleValue() + areaTriangle(factory.getLocation().getBlockX(),
			factory.getLocation().getBlockZ(), graphEdge.x1, graphEdge.y1, graphEdge.x1, graphEdge.y2));
	}

	private double areaTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		double area = (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
		return area > 0 ? area : -area;
	}
	
	public double getPercentArea(Factory factory) {
		return areas.get(factory)/totalArea;
	}
}
