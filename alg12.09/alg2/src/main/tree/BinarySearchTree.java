package tree;
import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {
    private Node raiz;

    private class Node {
        int valor;
        Node esquerda;
        Node direita;

        Node(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public BinarySearchTree() {
        this.raiz = null;
    }

    // Função para buscar um elemento
    @Override
    public boolean buscaElemento(int valor) {
        return busca(raiz, valor);
    }

    private boolean busca(Node node, int valor) {
        if (node == null) {
            return false;
        }
        if (valor == node.valor) {
            return true;
        } else if (valor < node.valor) {
            return busca(node.esquerda, valor);
        } else {
            return busca(node.direita, valor);
        }
    }

    // Função para encontrar o valor mínimo
    @Override
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia");
        }
        return min(raiz).valor;
    }

    private Node min(Node node) {
        if (node.esquerda == null) {
            return node;
        } else {
            return min(node.esquerda);
        }
    }

    // Função para encontrar o valor máximo
    @Override
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia");
        }
        return max(raiz).valor;
    }

    private Node max(Node node) {
        if (node.direita == null) {
            return node;
        } else {
            return max(node.direita);
        }
    }

    // Função para inserir um elemento
    @Override
    public void insereElemento(int valor) {
        raiz = insere(raiz, valor);
    }

    private Node insere(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }
        if (valor < node.valor) {
            node.esquerda = insere(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = insere(node.direita, valor);
        }
        return node;
    }

    // Função para remover um elemento
    @Override
    public void remove(int valor) {
        raiz = removeElemento(raiz, valor);
    }

    private Node removeElemento(Node node, int valor) {
        if (node == null) {
            return null;
        }
        if (valor < node.valor) {
            node.esquerda = removeElemento(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = removeElemento(node.direita, valor);
        } else {
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            }
            node.valor = min(node.direita).valor;
            node.direita = removeElemento(node.direita, node.valor);
        }
        return node;
    }

    // Função para percorrer em pré-ordem
    @Override
    public int[] preOrdem() {
        List<Integer> resultado = new ArrayList<>();
        preOrdemRec(raiz, resultado);
        return resultado.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdemRec(Node node, List<Integer> resultado) {
        if (node != null) {
            resultado.add(node.valor);
            preOrdemRec(node.esquerda, resultado);
            preOrdemRec(node.direita, resultado);
        }
    }

    // Função para percorrer em em-ordem
    @Override
    public int[] emOrdem() {
        List<Integer> resultado = new ArrayList<>();
        emOrdemRec(raiz, resultado);
        return resultado.stream().mapToInt(i -> i).toArray();
    }

    private void emOrdemRec(Node node, List<Integer> resultado) {
        if (node != null) {
            emOrdemRec(node.esquerda, resultado);
            resultado.add(node.valor);
            emOrdemRec(node.direita, resultado);
        }
    }

    // Função para percorrer em pós-ordem
    @Override
    public int[] posOrdem() {
        List<Integer> resultado = new ArrayList<>();
        posOrdemRec(raiz, resultado);
        return resultado.stream().mapToInt(i -> i).toArray();
    }

    private void posOrdemRec(Node node, List<Integer> resultado) {
        if (node != null) {
            posOrdemRec(node.esquerda, resultado);
            posOrdemRec(node.direita, resultado);
            resultado.add(node.valor);
        }
    }
}