package com.graphvisualizer.model

/**
 * Represents a directed graph with vertices and edges
 */
class Graph {
    // Map to store vertices and their enabled status
    private val vertices = mutableMapOf<String, Boolean>()

    // Store edges as pairs of strings (from vertex -> to vertex)
    private val edges = mutableSetOf<Pair<String, String>>()

    /**
     * Add a vertex to the graph
     */
    fun addVertex(vertex: String) {
        // Initialize as enabled by default
        if (!vertices.containsKey(vertex)) {
            vertices[vertex] = true
        }
    }

    /**
     * Add an edge between two vertices
     */
    fun addEdge(from: String, to: String) {
        // Add both vertices to ensure they exist
        addVertex(from)
        addVertex(to)

        // Add the edge
        edges.add(Pair(from, to))
    }

    /**
     * Toggle a vertex's enabled state
     */
    fun toggleVertex(vertex: String, enabled: Boolean) {
        if (vertices.containsKey(vertex)) {
            vertices[vertex] = enabled
        }
    }

    /**
     * Get all vertices in the graph
     */
    fun getAllVertices(): Set<String> {
        return vertices.keys
    }

    /**
     * Get only enabled vertices
     */
    fun getEnabledVertices(): Set<String> {
        return vertices.filter { it.value }.keys
    }

    /**
     * Get all edges in the graph
     */
    fun getAllEdges(): Set<Pair<String, String>> {
        return edges
    }

    /**
     * Get only edges where both vertices are enabled
     */
    fun getEnabledEdges(): Set<Pair<String, String>> {
        val enabledVertices = getEnabledVertices()
        return edges.filter {
            enabledVertices.contains(it.first) && enabledVertices.contains(it.second)
        }.toSet()
    }

    /**
     * Clear the graph
     */
    fun clear() {
        vertices.clear()
        edges.clear()
    }

    /**
     * Parse graph from edge list text
     * Format: "A -> B" for each line
     */
    fun parseFromText(text: String) {
        clear()

        text.lines().forEach { line ->
            val trimmedLine = line.trim()
            if (trimmedLine.isNotEmpty()) {
                // Split by "->" and handle spaces
                val parts = trimmedLine.split("->").map { it.trim() }
                if (parts.size == 2) {
                    addEdge(parts[0], parts[1])
                }
            }
        }
    }
}