package analyzers;

import expression_models.ExpressionTree;

public interface IExpressionAnalyzer {
    ExpressionTree generateTree(String[] tokens);
}
