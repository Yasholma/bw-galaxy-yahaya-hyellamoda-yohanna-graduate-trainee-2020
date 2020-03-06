import React, { Component } from "react";
import { Form, Button, notification, Select } from "antd";
import { assignTask, fetchAllTasks, fetchAllUsers } from "../util/APIUtils";
import LoadingIndicator from "../common/LoadingIndicator";
import { Role } from "./../util/Helpers";
import { Link } from "react-router-dom";

const FormItem = Form.Item;
const Option = Select.Option;

class AssignTask extends Component {
	constructor(props) {
		super(props);
		this.state = {
			loading: false,
			users: [],
			tasks: [],
			selectedUser: "",
			selectedTask: ""
		};

		notification.config({
			placement: "topRight",
			bottom: 70,
			duration: 5
		});

		this.handleSubmit = this.handleSubmit.bind(this);
		this.isFormInvalid = this.isFormInvalid.bind(this);
	}

	componentDidMount() {
		this.setState({ loading: true });
		fetchAllUsers()
			.then(res => {
				this.setState({ loading: false });
				const us = res.result
					.filter(u => u.roles[0].name !== Role.Admin)
					.reduce((total, curr, index) => {
						let { id, name, username, email } = curr;
						total[index] = {
							id,
							name,
							username,
							email
						};
						return total;
					}, []);

				this.setState({ users: us });
			})
			.catch(e => {
				this.setState({ loading: false });
				notification.error({
					message: "BW Galaxy TDMS",
					description: "Error fetching users: Network issues."
				});
			});
		fetchAllTasks()
			.then(res => {
				this.setState({ loading: false });
				const tasks = res.result.filter(task => !task.status);
				this.setState({ tasks: tasks });
			})
			.catch(e => {
				this.setState({ loading: false });
				notification.error({
					message: "BW Galaxy TDMS",
					description: "Error fetching tasks: Network issues."
				});
			});
	}

	handleMemberChange = value => {
		this.setState({ selectedUser: value.key });
	};

	handleTaskChange = value => {
		this.setState({ selectedTask: value.key });
	};

	handleSubmit(event) {
		event.preventDefault();
		this.setState({ assignLoading: true });
		const assignRequest = {
			userId: this.state.selectedUser,
			taskId: this.state.selectedTask
		};

		assignTask(assignRequest)
			.then(res => {
				this.setState({ assignLoading: false });
				if (res.status === 400) {
					notification.error({
						message: "BW Galaxy TDMS",
						description:
							res.message ||
							"Sorry! Something went wrong. Please try again!"
					});
				} else {
					notification.success({
						message: "BW Galaxy TDMS",
						description: "Task assigned successfully."
					});
					this.props.history.push("/admin/tasks");
				}
			})
			.catch(err => {
				this.setState({ assignLoading: false });
				notification.error({
					message: "BW Galaxy TDMS",
					description:
						err.message ||
						"Sorry! Something went wrong. Please try again!"
				});
			});
	}

	isFormInvalid() {
		return !(this.state.selectedUser && this.state.selectedTask);
	}

	render() {
		if (this.state.loading) {
			return <LoadingIndicator />;
		}
		return (
			<div
				className="newtask-container p-3"
				style={{
					borderRadius: "10px",
					boxShadow: "0 0 5px rgba(0, 0, 0, 0.1)"
				}}
			>
				<h1 className="page-title">Assign Task To User</h1>
				<div className="card p-2 pt-4 custom-shadow">
					<Form onSubmit={this.handleSubmit} className="newtask-form">
						<FormItem>
							<Select
								labelInValue
								defaultValue={{ key: "Select Member" }}
								onChange={this.handleMemberChange}
								size="large"
								name="member"
							>
								{this.state.users.map(user => (
									<Option value={user.id} key={user.id}>
										{user.name}
									</Option>
								))}
							</Select>
						</FormItem>
						<FormItem>
							<Select
								labelInValue
								size="large"
								defaultValue={{ key: "Select Task" }}
								onChange={this.handleTaskChange}
								name="task"
							>
								{this.state.tasks
									.sort((a, b) => a.id - b.id)
									.map(task => (
										<Option value={task.id} key={task.id}>
											{task.id}:{" "}
											{task.description.substring(0, 100)}
										</Option>
									))}
							</Select>
						</FormItem>

						<FormItem>
							{(this.state.assignLoading && (
								<LoadingIndicator />
							)) || (
								<Button
									type="primary"
									htmlType="submit"
									size="large"
									className="newtask-form-button"
									disabled={this.isFormInvalid()}
								>
									Proceed
								</Button>
							)}
						</FormItem>
						<Link to="/admin/tasks">{" << "}Back</Link>
					</Form>
				</div>
			</div>
		);
	}
}

export default AssignTask;
