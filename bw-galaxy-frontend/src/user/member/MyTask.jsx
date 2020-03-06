import React from "react";
import { Link } from "react-router-dom";
import { notification } from "antd";
import LoadingIndicator from "./../../common/LoadingIndicator";
import { fetchUserTasks, taskCompleted } from "./../../util/APIUtils";
import { Role, formatDateTime } from "./../../util/Helpers";

class MyTask extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			tasks: [],
			loading: false,
			user: this.props.currentUser,
			completeLoading: false
		};

		notification.config({
			placement: "bottomRight",
			bottom: 10,
			duration: 3
		});
	}

	confirmIsMember() {
		if (this.state.user.role !== Role.User) {
			this.props.history.push("/login");
		}
	}

	componentDidMount() {
		this.confirmIsMember();
		this.setState({ loading: true });

		const userId = this.state.user.id || 0;
		if (userId) {
			this.loadTask(userId);
		} else {
			notification.error({
				message: "BW Galaxy TDMS",
				description: "Error fetching your tasks: Network issues."
			});
		}
	}

	loadTask(userId) {
		fetchUserTasks(userId)
			.then(res => {
				this.setState({
					loading: false,
					tasks: res
				});
				notification.success({
					message: "BW Galaxy TDMS",
					description: "Tasks assigned to you."
				});
			})
			.catch(e => {
				this.setState({ loading: false });
				this.props.history.push("/login");
			});
	}

	completeTask = taskId => {
		if (window.confirm("Are you sure you are done with this task?")) {
			this.setState({ completeLoading: true });
			taskCompleted(taskId)
				.then(res => {
					this.setState({ completeLoading: false });
					notification.success({
						message: "BW Galaxy TDMS",
						description: "Congratulations on completing this task."
					});
					this.loadTask(this.state.user.id);
				})
				.catch(err => {
					this.setState({ completeLoading: false });
					notification.error({
						message: "BW Galaxy TDMS",
						description:
							"Error completing your action: Network issues. try again."
					});
				});
		}
	};

	render() {
		if (this.state.loading) {
			return <LoadingIndicator />;
		}
		if (!this.state.loading && this.state.tasks.length === 0) {
			return <h1 className="text-center display-4">No Task</h1>;
		}
		return (
			<>
				<div className="container-fluid mt-3">
					<div className="row">
						<div className="col-md-12">
							<h3 className="text-center border-bottom pb-2">
								My Tasks
							</h3>
							<table
								className="table table-responsive-sm table-bordered mt-2"
								style={{
									borderRadius: "10px ",
									borderWidth: "none",
									boxShadow: "0 0 5px rgba(0, 0, 0, 0.1)"
								}}
							>
								<thead>
									<tr>
										<th>Description</th>
										<th>Due Date</th>
										<th>Status</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									{this.state.tasks.map(task => (
										<tr key={task.id}>
											<td>{task.description}</td>
											<td>
												<strong className="text-danger">
													{formatDateTime(
														task.deadline
													)}
												</strong>
											</td>
											<td>
												{task.status ? (
													<span className="badge p-2 badge-pill badge-success">
														Completed
													</span>
												) : (
													<span className="badge p-2 badge-pill badge-warning">
														Pending
													</span>
												)}
											</td>
											<td>
												<Link
													to={`/member/taskview/${task.id}`}
													className="btn btn-sm btn-outline-info mb-1 mx-1"
												>
													View
												</Link>
												{(this.state
													.completeLoading && (
													<div className="text-center">
														<div
															className=" spinner-border spinner-border-sm"
															role="status"
														>
															<span className="sr-only">
																Loading...
															</span>
														</div>
													</div>
												)) ||
													(!task.status && (
														<button
															onClick={() =>
																this.completeTask(
																	task.id
																)
															}
															className="btn btn-sm mb-1 mx-1 btn-danger"
														>
															Done
														</button>
													))}
											</td>
										</tr>
									))}
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</>
		);
	}
}

export default MyTask;
