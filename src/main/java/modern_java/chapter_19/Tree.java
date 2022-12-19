package modern_java.chapter_19;

public class Tree {
    private String key;
    private int val;
    private Tree left, right;

    public Tree(String key, int val, Tree left, Tree right) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static class TreeProcessor {
        public static int lookup(String key, int defaultVal, Tree tree) {
            if (tree == null) {
                return defaultVal;
            }

            if (key.equals(tree.key)) {
                return tree.val;
            }

            return lookup(key, defaultVal,
                    key.compareTo(tree.key) < 0 ? tree.left : tree.right);
        }

        public static Tree update(String key, int newVal, Tree tree) {
            if (tree == null) {
                //새로운 노드 추가
                tree = new Tree(key, newVal, null, null);
            } else if (key.equals(tree.key)) {
                tree.val = newVal;
            } else if (key.compareTo(tree.key) < 0) {
                tree.left = update(key, newVal, tree.right);
            } else {
                tree.right = update(key, newVal, tree.right);
            }

            return tree;
        }

        public static Tree fupdate(String key, int newVal, Tree tree) {
            return (tree == null) ?
                    new Tree(key, newVal, null, null) :
                    key.equals(tree.key) ?
                            new Tree(key, newVal, tree.left, tree.right) :
                            key.compareTo(tree.key) < 0 ?
                                    new Tree(tree.key, tree.val, fupdate(key, newVal, tree.left), tree.right) :
                                    new Tree(tree.key, tree.val, tree.left, fupdate(key, newVal, tree.right));
        }
    }
}
