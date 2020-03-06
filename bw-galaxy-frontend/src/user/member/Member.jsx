import React from "react";
import { Switch, Route } from "react-router-dom";
import "./Member.css";
import MyTask from "./MyTask";
import { ViewTask } from "./ViewTask";
import MemberDashboard from "./MemberDashboard";

function Member(props) {
	const currentUser = props.currentUser;
	return (
		<Switch>
			<Route
				path="/member/taskview/:taskId"
				render={props => (
					<ViewTask currentUser={currentUser} {...props} />
				)}
			/>
			<Route
				path="/member/tasklist"
				render={() => (
					<MyTask currentUser={props.currentUser} {...props} />
				)}
			/>
			<Route path="/member" render={() => <MemberDashboard />} />
		</Switch>
	);
}

export default Member;
