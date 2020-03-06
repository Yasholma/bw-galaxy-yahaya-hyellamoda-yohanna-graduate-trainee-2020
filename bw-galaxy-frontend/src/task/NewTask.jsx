import React, { Component } from "react";
import "./NewTask.css";
import { Link } from "react-router-dom";

import { Form, Input, Button, notification, DatePicker } from "antd";
import { createTask } from "../util/APIUtils";
import LoadingIndicator from "./../common/LoadingIndicator";
const { TextArea } = Input;
const FormItem = Form.Item;

class NewTask extends Component {
	constructor(props) {
		super(props);
		this.state = {
			description: {
				value: ""
			},
			details: {
				value: "",
				validateStatus: "success"
			},
			deadline: {
				value: ""
			},
			loading: false
		};

		this.handleSubmit = this.handleSubmit.bind(this);
		this.isFormInvalid = this.isFormInvalid.bind(this);
	}

	handleInputChange = (event, validationFun) => {
		const target = event.target;
		const inputName = target.name;
		const inputValue = target.value;

		this.setState({
			[inputName]: {
				value: inputValue,
				...validationFun(inputValue)
			}
		});
	};

	handleSubmit(event) {
		event.preventDefault();
		this.setState({ loading: true });
		const taskRequest = {
			description: this.state.description.value,
			details: this.state.details.value,
			deadline: this.state.deadline.value
		};

		createTask(taskRequest)
			.then(res => {
				this.setState({ loading: false });
				notification.success({
					message: "Task Management",
					description: "Task created successfully."
				});
				this.props.history.push("/admin/tasks");
			})
			.catch(error => {
				this.setState({ loading: false });
				notification.error({
					message: "Task Management",
					description:
						error.message ||
						"Sorry! Something went wrong. Please try again!"
				});
			});
	}

	isFormInvalid() {
		return !(
			this.state.description.validateStatus === "success" &&
			this.state.details.validateStatus === "success" &&
			this.state.deadline.validateStatus === "success"
		);
	}

	deadline = (v, d) => {
		this.setState({
			deadline: {
				value: d
			}
		});
	};

	render() {
		return (
			<div
				className="newtask-container custom-shadow p-3 mb-3"
				style={{
					borderRadius: "10px",
					boxShadow: "0 0 5px rgba(0, 0, 0, 0.1)"
				}}
			>
				<h1 className="page-title">Create New Task</h1>
				<div className="newtask-content ">
					<Form onSubmit={this.handleSubmit} className="newtask-form">
						<FormItem
							label="Task Description"
							validateStatus={
								this.state.description.validateStatus
							}
							help={this.state.description.errorMsg}
						>
							<Input
								size="large"
								name="description"
								placeholder="Task Description"
								value={this.state.description.value}
								onChange={event =>
									this.handleInputChange(
										event,
										this.validateDescription
									)
								}
							/>
						</FormItem>
						<FormItem
							label="Details"
							hasFeedback
							validateStatus={this.state.details.validateStatus}
							help={this.state.details.errorMsg}
						>
							<TextArea
								placeholder="Enter Details (Optional)"
								rows={3}
								name="details"
								value={this.state.details.value}
								onChange={event =>
									this.handleInputChange(
										event,
										this.validateDetails
									)
								}
							/>
						</FormItem>
						<FormItem label="Deadline (Click OK after selection)">
							<DatePicker
								showTime
								onChange={this.deadline}
								onOk={value =>
									this.setState(prevState => ({
										deadline: {
											value: prevState.deadline.value,
											validateStatus: "success"
										}
									}))
								}
							/>
						</FormItem>

						<FormItem>
							{(this.state.loading && <LoadingIndicator />) || (
								<Button
									type="primary"
									htmlType="submit"
									size="large"
									className="newtask-form-button"
									disabled={this.isFormInvalid()}
								>
									Save Task
								</Button>
							)}
						</FormItem>
						<Link to="/admin/tasks">{" << "}Back</Link>
					</Form>
				</div>
			</div>
		);
	}

	// Validation Functions
	validateDescription = description => {
		if (description.length < 10) {
			return {
				validateStatus: "error",
				errorMsg: `Description is too short (Minimum 10 characters needed.)`
			};
		} else if (description.length > 240) {
			return {
				validationStatus: "error",
				errorMsg: `Description is too long (Maximum 240 characters allowed.)`
			};
		} else {
			return {
				validateStatus: "success",
				errorMsg: null
			};
		}
	};

	validateDetails = details => {
		if (details.length !== 0) {
			if (details.length < 20) {
				return {
					validateStatus: "error",
					errorMsg: `Details is too short (Minimum 20 characters needed.)`
				};
			} else {
				return {
					validateStatus: "success",
					errorMsg: null
				};
			}
		} else {
			return {
				validateStatus: "success",
				errorMsg: null
			};
		}
	};

	validateDeadline = () => {
		if (this.state.deadline === "") {
			return {
				validateStatus: "error",
				errorMsg: "Select a due date for the task."
			};
		} else {
			return {
				validateStatus: "success",
				errorMsg: null
			};
		}
	};
}

export default NewTask;
