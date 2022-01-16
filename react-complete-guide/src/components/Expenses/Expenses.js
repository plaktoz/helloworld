import ExpenseItem from "./ExpenseItem";
import Card from "../UI/Card"

import "./Expenses.css";

const Expenses = (props) => {
    const expenses = props.items;
    return (
        <Card className="expenses">
          {expenses.map((expense, index) => {
            return (
              <ExpenseItem
                key={expense.id}
                title={expense.title}
                amount={expense.amount}
                date={expense.date}
              />
            );
          })}
        </Card>
      );
}

export default Expenses;
