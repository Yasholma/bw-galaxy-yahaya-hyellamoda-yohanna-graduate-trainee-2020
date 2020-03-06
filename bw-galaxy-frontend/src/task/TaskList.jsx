import React from "react";
import { formatDateTime } from "./../util/Helpers";
import LoadingIndicator from "../common/LoadingIndicator";
import {
	fetchAllTasks,
	completedTask,
	pendingTasks,
	deleteTaskById
} from "../util/APIUtils";
import { Link } from "react-router-dom";
import { notification } from "antd";

class TaskList extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			tasks: [],
			loading: false
		};

		notification.config({
			placement: "bottomRight",
			bottom: 10,
			duration: 3
		});
	}

	componentDidMount() {
		this.setState({ loading: true });
		this.loadTask();
	}

	loadTask() {
		fetchAllTasks()
			.then(res => {
				this.setState({
					loading: false,
					tasks: res.result
				});
			})
			.catch(e => {
				this.props.history.push("/login");
			});
	}

	allTask = () => {
		fetchAllTasks().then(res => {
			this.setState({ tasks: res.result });
		});
	};

	pendingTask = () => {
		pendingTasks()
			.then(res => {
				this.setState({ tasks: res.result });
				notification.success({
					message: "BW Galaxy TDMS",
					description: "Pending tasks."
				});
			})
			.catch(err => {
				notification.error({
					message: "BW Galaxy TDMS",
					description: "Error fetching pending tasks: Network issues."
				});
			});
	};

	completedTask = () => {
		completedTask()
			.then(res => {
				this.setState({ tasks: res.result });
				notification.success({
					message: "BW Galaxy TDMS",
					description: "Completed tasks."
				});
			})
			.catch(err => {
				notification.error({
					message: "BW Galaxy TDMS",
					description:
						"Error fetching competed tasks: Network issues."
				});
			});
	};

	deleteTask = taskId => {
		this.setState({ deleteLoading: true });
		deleteTaskById(taskId)
			.then(() => {
				this.setState({ deleteLoading: false });
				this.setState({
					tasks: this.state.tasks.filter(
						task => task.id !== Number(taskId)
					)
				});
				notification.success({
					message: "BW Galaxy TDMS",
					description: "Task has been successfully deleted."
				});
			})
			.catch(err => {
				this.setState({ deleteLoading: false });
				this.props.history.push("/admin/tasks");
				notification.error({
					message: "BW Galaxy TDMS",
					description: "Error deleting task: Network issues."
				});
			});
	};

	render() {
		if (this.state.loading) {
			return <LoadingIndicator />;
		}
		return (
			<>
				<div className="container-fluid mt-3">
					<div className="row">
						<div className="col-12">
							<Link
								to="/admin/addTask"
								className="btn btn-sm btn-danger mr-1 mb-1"
							>
								Add Task
							</Link>
							<button
								className="btn btn-sm btn-secondary mb-1"
								onClick={this.allTask}
							>
								All Task
							</button>
							<button
								className="btn btn-sm btn-secondary mx-1 mb-1"
								onClick={this.pendingTask}
							>
								Pending Task
							</button>
							<button
								className="btn btn-sm btn-secondary mr-1 mb-1"
								onClick={this.completedTask}
							>
								Completed Task
							</button>
							<Link
								to="/admin/assignTask"
								className="btn btn-success btn-sm float-right mb-1"
							>
								Assign Task
							</Link>
						</div>
					</div>
					<div className="row">
						<div className="col-md-12">
							<table
								className="table table-responsive-sm  table-bordered mt-2"
								style={{
									borderRadius: "10px",
									borderWidth: 0,
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
												{formatDateTime(task.deadline)}
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
											<td className="text-center">
												<Link
													to={`/admin/task/${task.id}`}
													className="btn btn-sm btn-outline-info my-1 mx-1"
												>
													View
												</Link>
												{(this.state.deleteLoading && (
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
												)) || (
													<button
														onClick={() =>
															this.deleteTask(
																task.id
															)
														}
														className="btn btn-sm btn-outline-danger"
													>
														Delete
													</button>
												)}
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

export default TaskList;
