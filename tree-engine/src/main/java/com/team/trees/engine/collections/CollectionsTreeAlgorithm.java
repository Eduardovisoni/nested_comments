package com.team.trees.engine.collections;

import com.team.trees.engine.TreeAlgorithmStrategy;
import com.team.trees.engine.TreeView;
import com.team.trees.engine.model.PlainNode;
import java.util.*;

public class CollectionsTreeAlgorithm implements TreeAlgorithmStrategy {

    @Override
    public TreeView buildTree(List<PlainNode> nodes) {
        // Indexamos por id para acceso O(1)
        Map<Long, List<PlainNode>> childrenByParent = new HashMap<>();
        for (PlainNode n : nodes) {
            childrenByParent.computeIfAbsent(n.parentId(), k -> new ArrayList<>()).add(n);
        }
        PlainNode root = nodes.stream()
            .filter(n -> n.parentId() == null)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No root found"));
        return build(root, childrenByParent);
    }

    private TreeView build(PlainNode current, Map<Long, List<PlainNode>> childrenByParent) {
        List<TreeView> children = childrenByParent.getOrDefault(current.id(), List.of())
            .stream().map(c -> build(c, childrenByParent)).toList();
        return new TreeView(current.id(), current.value(), children);
    }

    @Override
    public List<Long> dfs(List<PlainNode> nodes) {
    	// Construimos el mapa de hijos igual que en buildTree
        Map<Long, List<PlainNode>> childrenByParent = new HashMap<>();
        for (PlainNode n : nodes) {
            childrenByParent.computeIfAbsent(n.parentId(), k -> new ArrayList<>()).add(n);
        }
        PlainNode root = nodes.stream()
            .filter(n -> n.parentId() == null)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No root found"));

        List<Long> result = new ArrayList<>();
        // Usamos ArrayDeque como pila: push agrega al frente, pop saca del frente
        ArrayDeque<PlainNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            PlainNode current = stack.pop();
            result.add(current.id());
            // Agregamos los hijos a la pila (en orden inverso para mantener orden natural)
            List<PlainNode> children = childrenByParent.getOrDefault(current.id(), List.of());
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return result; 
    }

    @Override
    public List<Long> bfs(List<PlainNode> nodes) {
    	// Construimos el mapa de hijos igual que en buildTree
        Map<Long, List<PlainNode>> childrenByParent = new HashMap<>();
        for (PlainNode n : nodes) {
            childrenByParent.computeIfAbsent(n.parentId(), k -> new ArrayList<>()).add(n);
        }
        PlainNode root = nodes.stream()
            .filter(n -> n.parentId() == null)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No root found"));

        List<Long> result = new ArrayList<>();
        // Usamos ArrayDeque como cola: offer agrega al final, poll saca del frente
        ArrayDeque<PlainNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            PlainNode current = queue.poll();
            result.add(current.id());
            // Agregamos los hijos al final de la cola (nivel por nivel)
            List<PlainNode> children = childrenByParent.getOrDefault(current.id(), List.of());
            for (PlainNode child : children) {
                queue.offer(child);
            }
        }
        return result; 
    }

    @Override
    public TreeView buildSubTree(List<PlainNode> nodes, Long rootId) {
    	// Índice de hijos por padre para BFS eficiente
        Map<Long, List<PlainNode>> childrenByParent = new HashMap<>();
        Map<Long, PlainNode> byId = new HashMap<>();
        for (PlainNode n : nodes) {
            childrenByParent.computeIfAbsent(n.parentId(), k -> new ArrayList<>()).add(n);
            byId.put(n.id(), n);
        }
        // BFS: recolectar solo los nodos del subárbol
        List<PlainNode> sub = new ArrayList<>();
        ArrayDeque<Long> queue = new ArrayDeque<>();
        queue.offer(rootId);
        while (!queue.isEmpty()) {
            Long curr = queue.poll();
            PlainNode node = byId.get(curr);
            if (node == null) continue;
            // La raíz del subárbol no tiene padre en este contexto
            sub.add(curr.equals(rootId) ? new PlainNode(node.id(), node.value(), null) : node);
            childrenByParent.getOrDefault(curr, List.of()).forEach(c -> queue.offer(c.id()));
        }
        return buildTree(sub);
    }

    @Override
    public List<Long> pathToRoot(List<PlainNode> nodes, Long nodeId) {
    	Map<Long, Long> parentMap = new HashMap<>();
        for (PlainNode n : nodes) {
            if (n.parentId() != null) parentMap.put(n.id(), n.parentId());
        }
        LinkedList<Long> path = new LinkedList<>();
        Long current = nodeId;
        while (current != null) {
            path.addFirst(current);       // inserta al frente → orden raíz → nodo
            current = parentMap.get(current);
        }
        return new ArrayList<>(path);
    }

    @Override
    public int height(List<PlainNode> nodes) {
        throw new UnsupportedOperationException("TODO Semana 2");
    }

    @Override
    public int depth(List<PlainNode> nodes, Long nodeId) {
        throw new UnsupportedOperationException("TODO Semana 2");
    }

    @Override
    public List<Long> ancestors(List<PlainNode> nodes, Long nodeId) {
        throw new UnsupportedOperationException("TODO Semana 2");
    }

    @Override
    public boolean hasNoCycles(List<PlainNode> nodes) {
        throw new UnsupportedOperationException("TODO Semana 2");
    }
}