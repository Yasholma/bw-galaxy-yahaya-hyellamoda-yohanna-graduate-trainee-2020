import React, { Component } from "react";
import "./App.css";
import { Route, withRouter, Switch } from "react-router-dom";

import { getCurrentUser } from "../util/APIUtils";
import { ACCESS_TOKEN } from "../constants";

import Login from "../user/login/Login";
import AppHeader from "../common/AppHeader";
import NotFound from "../common/NotFound";

import { Layout, notification } from "antd";
import Admin from "../user/admin/Admin";
import { Role } from "../util/Helpers";
import PrivateRoute from "./../common/PrivateRoute";
import Member from "./../user/member/Member";
const { Content } = Layout;

class App extends Component {
	constructor(props) {
		super(props);
		this.state = {
			currentUser: null,
			isAuthenticated: false,
			isAdmin: false
		};
		this.handleLogout = this.handleLogout.bind(this);
		this.loadCurrentUser = this.loadCurrentUser.bind(this);
		this.handleLogin = this.handleLogin.bind(this);

		notification.config({
			placement: "topRight",
			top: 70,
			duration: 3
		});
	}

	loadCurrentUser() {
		getCurrentUser()
			.then(response => {
				this.setState(
					{
						currentUser: response,
						isAuthenticated: true,
						isAdmin: response.role === Role.Admin
					},
					() => {
						if (this.state.isAdmin) {
							this.props.history.push("/admin");
						} else {
							this.props.history.push("/member");
						}
					}
				);
			})
			.catch(error => {
				this.setState({
					isAuthenticated: false,
					currentUser: null
				});
			});
	}

	componentDidMount() {
		this.loadCurrentUser();
	}

	handleLogout(
		redirectTo = "/login",
		notificationType = "success",
		description = "You're successfully logged out."
	) {
		localStorage.removeItem(ACCESS_TOKEN);

		this.setState({
			currentUser: null,
			isAuthenticated: false
		});

		this.props.history.push(redirectTo);

		notification[notificationType]({
			message: "BW Galaxy TDMS",
			description: description
		});
	}

	handleLogin() {
		notification.success({
			message: "BW Galaxy TDMS",
			description: "You're successfully logged in."
		});
		this.loadCurrentUser();
	}

	render() {
		return (
			<Layout className="app-container">
				<AppHeader
					isAuthenticated={this.state.isAuthenticated}
					currentUser={this.state.currentUser}
					onLogout={this.handleLogout}
				/>

				<Content className="app-content">
					<div className="container">
						<Switch>
							<PrivateRoute
								authenticated={this.state.isAuthenticated}
								path="/admin"
								component={Admin}
								handleLogout={this.handleLogout}
							/>
							<PrivateRoute
								authenticated={this.state.isAuthenticated}
								path="/member"
								component={Member}
								currentUser={this.state.currentUser}
								handleLogout={this.handleLogout}
							/>
							<Route
								path="/login"
								exact
								render={props => (
									<Login
										onLogin={this.handleLogin}
										{...props}
										isAuthenticated={
											this.state.isAuthenticated
										}
										isAdmin={this.state.isAdmin}
									/>
								)}
							/>
							<Route
								path="/"
								exact
								render={props => (
									<Login
										onLogin={this.handleLogin}
										{...props}
										isAuthenticated={
											this.state.isAuthenticated
										}
										isAdmin={this.state.isAdmin}
									/>
								)}
							/>
							<Route component={NotFound}></Route>
						</Switch>
					</div>
				</Content>
			</Layout>
		);
	}
}

export default withRouter(App);
