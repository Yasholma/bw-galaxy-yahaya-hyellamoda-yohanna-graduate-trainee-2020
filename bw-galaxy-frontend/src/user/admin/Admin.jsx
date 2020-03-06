import React from "react";
import "./Admin.css";
import { Switch, Route } from "react-router-dom";
import NewTask from "./../../task/NewTask";
import AssignTask from "./../../task/AssignTask";
import AddMember from "./AddMember";
import TaskList from "./../../task/TaskList";
import { ViewTask } from "./../../task/ViewTask";
import EditTask from "./../../task/EditTask";
import AdminDashboard from "./AdminDashboard";
import { MemberList } from "./MemberList";
import { ViewMember } from "./ViewMember";

function Admin(props) {
	return (
		<Switch>
			<Route path="/admin/assignTask" component={AssignTask} />
			<Route path="/admin/member/:memberId" component={ViewMember} />
			<Route path="/admin/register" component={AddMember} />
			<Route path="/admin/members" component={MemberList} />
			<Route path="/admin/task/edit/:taskId" component={EditTask} />
			<Route path="/admin/task/:taskId" component={ViewTask} />
			<Route path="/admin/addTask" component={NewTask} />
			<Route path="/admin/tasks" component={TaskList} />
			<Route path="/admin" render={() => <AdminDashboard />} />
		</Switch>
	);
}

export default Admin;
