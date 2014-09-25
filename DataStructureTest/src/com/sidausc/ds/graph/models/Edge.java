package com.sidausc.ds.graph.models;

public class Edge {
	private String id;
	private Vertex source;
	private Vertex destination;
	private int weight;
	public Edge(String id, Vertex source, Vertex destination, int weight) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	public String getId() {
		return id;
	}
	public Vertex getSource() {
		return source;
	}
	public Vertex getDestination() {
		return destination;
	}
	public int getWeight() {
		return weight;
	}
}
