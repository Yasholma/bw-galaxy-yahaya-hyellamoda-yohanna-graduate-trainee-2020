import React, { Component } from "react";
import { login } from "../../util/APIUtils";
import "./Login.css";
import { ACCESS_TOKEN } from "../../constants";

import { Form, Input, Button, Icon, notification } from "antd";
import LoadingIndicator from "./../../common/LoadingIndicator";
const FormItem = Form.Item;

class Login extends Component {
	checkAuth() {
		const { isAuthenticated, isAdmin } = this.props;
		if (isAuthenticated) {
			if (isAdmin) {
				this.props.history.push("/admin");
			} else {
				this.props.history.push("/member");
			}
		}
	}

	componentDidMount() {
		this.checkAuth();
	}

	render() {
		const AntWrappedLoginForm = Form.create()(LoginForm);
		return (
			<div className="login-container">
				<h1 className="page-title">Login</h1>
				<div className="login-content">
					<AntWrappedLoginForm onLogin={this.props.onLogin} />
				</div>
			</div>
		);
	}
}

class LoginForm extends Component {
	constructor(props) {
		super(props);
		this.state = {
			isLoading: false
		};
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(event) {
		event.preventDefault();
		this.props.form.validateFields((err, values) => {
			if (!err) {
				this.setState({ isLoading: true });
				const loginRequest = Object.assign({}, values);
				login(loginRequest)
					.then(response => {
						const { accessToken, authority: role } = response;
						localStorage.setItem(ACCESS_TOKEN, accessToken);
						this.props.onLogin(role);
					})
					.catch(error => {
						this.setState({ isLoading: false });
						if (error.status === 401) {
							notification.error({
								message: "BW Galaxy TDMS",
								description:
									"Your Username or Password is incorrect. Please try again!"
							});
						} else {
							notification.error({
								message: "BW Galaxy TDMS",
								description:
									"Sorry! Something went wrong. Please try again!"
							});
						}
					});
			}
		});
	}

	render() {
		const { getFieldDecorator } = this.props.form;
		return (
			<Form
				onSubmit={this.handleSubmit}
				className="login-form p-3"
				style={{
					borderRadius: "10px",
					boxShadow: "0 0 5px rgba(0, 0, 0, 0.1)"
				}}
			>
				<FormItem className="form-group">
					{getFieldDecorator("usernameOrEmail", {
						rules: [
							{
								required: true,
								message: "Please input your username or email!"
							}
						]
					})(
						<Input
							prefix={<Icon type="user" />}
							size="large"
							name="usernameOrEmail"
							placeholder="Username or Email"
						/>
					)}
				</FormItem>
				<FormItem className="form-group">
					{getFieldDecorator("password", {
						rules: [
							{
								required: true,
								message: "Please input your Password!"
							}
						]
					})(
						<Input
							prefix={<Icon type="lock" />}
							size="large"
							name="password"
							type="password"
							placeholder="Password"
						/>
					)}
				</FormItem>
				<FormItem>
					{!this.state.isLoading && (
						<Button
							type="primary"
							htmlType="submit"
							size="large"
							className="login-form-button"
						>
							Login
						</Button>
					)}
					{this.state.isLoading && <LoadingIndicator />}
				</FormItem>
			</Form>
		);
	}
}

export default Login;
