import java.util.Stack;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * Arithmetic Expression Evaluation
 * An important application of stacks is in parsing. 
 * For example, a compiler must parse arithmetic expressions 
 * written using infix notation. For example the following 
 * infix expression evaluates to 212:
 *   ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
 *   
 * @author Sneha Parihar MSE W14
 */
public class ArithmeticExpression extends ConsoleProgram {

	public void run() {
		String infix = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";
		double result = evaluate( infix );
		println( result );
	}

	
	/**
	 * Parse and evaluate a postfix expression. 
	 * We read the tokens in one at a time. 
	 * If it is an integer, push it on the stack; 
	 * if it is a binary operator, pop the top two elements from the stack, 
	 * apply the operator to the two elements, and push the result back on the stack.
	 * @param string postfix
	 * @return result of the expression
	 */
	private double evaluate(String postfix) {
		double result = 0;
		StringTokenizer tokenizer = new StringTokenizer(postfix, " ");
		Stack<String> opStack = new Stack<String>();
		Stack<Double> valStack = new Stack<Double>();
		
		while(tokenizer.hasMoreTokens())
		{
			String token = tokenizer.nextToken();
			if(token.equals("(")){
				
			}
			else if(token.equals("+")){
				opStack.push(token);
			}
			else if(token.equals("*")){
				opStack.push(token);
			}
			else if(token.equals(")")){
				String op = opStack.pop();
				if(op.equals("+")){
					valStack.push(valStack.pop()+valStack.pop());
				}
				else if(op.equals("*")){
					valStack.push(valStack.pop()*valStack.pop());
				}
			}
			else{
				valStack.push(Double.parseDouble(token));
			}
				
			
		}
		result=valStack.pop();
		return result;
	}
}
