import React, { Component } from "react";
import { Link } from "react-router-dom";
import { notification, Collapse, List } from "antd";
import { fetchTask, fetchTaskUser, fetchUser } from "../../util/APIUtils";
import LoadingIndicator from "../../common/LoadingIndicator";
import { fetchUserTasks } from "../../util/APIUtils";
const { Panel } = Collapse;

export class ViewMember extends Component {
	constructor(props) {
		super(props);
		this.state = {
			tasks: [],
			user: null,
			loading: false
		};
	}

	componentDidMount() {
		this.setState({ loading: true });
		let memberId = this.props.match.params.memberId || 0;
		if (memberId === 0) this.props.history.push("/admin/tasks");
		memberId = Number(memberId);

		this.loadUser(memberId);
	}

	loadUser(userId) {
		this.setState({ loading: true });
		fetchUser(userId).then(response => {
			let u = {
				name: response.result[0],
				username: response.result[1],
				email: response.result[2],
				id: userId
			};
			this.setState({ user: u, loading: false });
			fetchUserTasks(userId).then(res => {
				this.setState({ tasks: res });
			});
		});
	}

	loadTask(taskId) {
		fetchTask(taskId)
			.then(res => {
				this.setState({ task: res.result, loading: false });
				fetchTaskUser(taskId).then(re => {
					let userId = re.result;
					if (userId !== null) {
						fetchUser(userId)
							.then(response => {})
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
					description: "Error fetching user info: Network issues."
				});
			});
	}

	render() {
		if (this.state.loading) {
			return <LoadingIndicator />;
		}
		if (this.state.user === null && !this.state.loading) {
			return <h1>No Data</h1>;
		}
		return (
			<div className="container-fluid mt-3">
				<h4 className="text-center mb-3">User Information</h4>
				<Collapse defaultActiveKey={["1"]}>
					<Panel header="Name" key="1">
						<h5>{this.state.user.name}</h5>
					</Panel>
					<Panel header="Username" key="2">
						<h6>@{this.state.user.username}</h6>
					</Panel>
					<Panel header="Email" key="3">
						<h6>{this.state.user.email}</h6>
					</Panel>
					<Panel
						header={`Task Assigned (${this.state.tasks.length})`}
						key="4"
					>
						<List
							dataSource={this.state.tasks}
							renderItem={task => (
								<List.Item key={task.id}>
									<List.Item.Meta
										title={
											<Link to={`/admin/task/${task.id}`}>
												{task.description.substring(
													0,
													100
												)}
											</Link>
										}
									/>
									<div>
										{task.status ? (
											<span className="badge p-2 badge-pill badge-success">
												Completed
											</span>
										) : (
											<span className="badge p-2 badge-pill badge-warning">
												Pending
											</span>
										)}
									</div>
								</List.Item>
							)}
						></List>
					</Panel>
				</Collapse>
				<br />
				<div className="row">
					<div className="col-md-12">
						<Link
							to="/admin/members"
							className="btn btn-secondary float-left"
						>
							{" << "} Back
						</Link>
					</div>
				</div>
			</div>
		);
	}
}
