import React, { Component } from "react";
import { fetchTask, fetchTaskUser } from "../util/APIUtils";
import LoadingIndicator from "./../common/LoadingIndicator";
import { notification, Collapse, Badge } from "antd";
import { fetchUser } from "./../util/APIUtils";
import { Link } from "react-router-dom";
import { formatDateTime } from "../util/Helpers";
const { Panel } = Collapse;

export class ViewTask extends Component {
	constructor(props) {
		super(props);
		this.state = {
			task: null,
			user: null,
			loading: false
		};
	}

	componentDidMount() {
		this.setState({ loading: true });
		let taskId = this.props.match.params.taskId || 0;
		if (taskId === 0) this.props.history.push("/admin/tasks");
		taskId = Number(taskId);
		this.loadTask(taskId);
	}

	loadTask(taskId) {
		fetchTask(taskId)
			.then(res => {
				this.setState({ task: res.result, loading: false });
				fetchTaskUser(taskId).then(re => {
					let userId = re.result;
					if (userId !== null) {
						fetchUser(userId)
							.then(response => {
								let u = {
									name: response.result[0],
									username: response.result[1],
									email: response.result[2]
								};
								this.setState({ user: u });
							})
							.catch(err => {
								this.setState({ loading: false, task: null });
								notification.error({
									message: "Task Management",
									description:
										"Error fetching user info: Network issues."
								});
							});
						notification.success({
							message: "Task Management",
							description: "Task Info"
						});
					} else {
						this.setState({ user: null });
					}
				});
			})
			.catch(err => {
				this.setState({ loading: false, task: null });
				notification.error({
					message: "Task Management",
					description: "Error fetching task info: Network issues."
				});
			});
	}

	render() {
		if (this.state.loading) {
			return <LoadingIndicator />;
		}
		if (this.state.task === null && !this.state.loading) {
			return <h1>No Data</h1>;
		}
		return (
			<div className="container-fluid mt-3">
				<h4 className="text-center mb-3">Task Information</h4>
				<Collapse defaultActiveKey={["1"]}>
					<Panel header="Description" key="1">
						<h5>{this.state.task.description}</h5>
					</Panel>
					<Panel header="Details" key="2">
						<p>{this.state.task.details}</p>
					</Panel>
					<Panel header="Due Date" key="3">
						<h5>{formatDateTime(this.state.task.deadline)}</h5>
					</Panel>
					<Panel header="Status" key="4">
						{this.state.task.status ? (
							<Badge status="success" text="Completed" />
						) : (
							<Badge status="warning" text="Pending" />
						)}
						<hr />
						{this.state.user ? (
							<h5>
								{"Task Assigned to: "}
								<strong>{`${this.state.user.name}`}</strong>
							</h5>
						) : (
							<p>{`Task has not been assigned to a member yet. `}</p>
						)}
					</Panel>
				</Collapse>
				<br />
				<div className="row">
					<div className="col-md-12">
						<Link
							to="/admin/tasks"
							className="btn btn-secondary float-left"
						>
							{" << "} Back
						</Link>
						{!this.state.task.status && (
							<Link
								to={`/admin/task/edit/${this.state.task.id}`}
								className="btn btn-success float-right"
							>
								Edit Task
							</Link>
						)}
					</div>
				</div>
			</div>
		);
	}
}
