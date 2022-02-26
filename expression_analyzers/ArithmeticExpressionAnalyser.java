package expression_analyzers;

import expression_models.ExpressionTree;

/*
    Arithmetic expression is an expression that can always be evaluated to a single integer
    This class analyzes arithmetic expressions and generates an expression tree
    e.g :
        1 + 2 * 3 + 1 => 8
 */
public class ArithmeticExpressionAnalyser implements IExpressionAnalyzer {
    
    private ExpressionTree generateTree(String[] tokens, int start, int end){
        if(start < 0 || end >= tokens.length || start > end){
            return null;
        }
        ExpressionTree res = null;
        if(start == end){
            return generateLeaf(tokens[start]);
        }

        for(int i=start; i <= end; i++){
            String token = tokens[i];
            if(token.equals("+") || token.equals("-")){
                res = new ExpressionTree(token);
                res.left = generateTree(tokens, start, i-1);
                res.right = generateTree(tokens , i+1 ,end);
                return res;
            }
        }

        for(int i=start; i <= end; i++){
            String token = tokens[i];
            if(token.equals("*")){
                res = new ExpressionTree(token);
                res.left = generateTree(tokens, start, i-1);
                res.right = generateTree(tokens , i+1 ,end);
                return res;
            }
        }
        return null;
    }

    private ExpressionTree generateLeaf(String leaf){
        ExpressionTree toReturn = null;
        if(leaf.startsWith("++")){
            toReturn = new ExpressionTree("++");
            toReturn.right = new ExpressionTree(leaf.substring(2));
        } else if(leaf.endsWith("++")){
            toReturn = new ExpressionTree("++");
            toReturn.left = new ExpressionTree(leaf.substring(0,leaf.length()-2));
        } else{
            toReturn = new ExpressionTree(leaf);
        }
        return toReturn;
    }

    @Override
    public ExpressionTree generateTree(String[] tokens) {
        return generateTree(tokens, 0, tokens.length-1);
    }
}