import React, { Component } from "react";
import { fetchUserTask } from "../../util/APIUtils";
import LoadingIndicator from "./../../common/LoadingIndicator";
import { notification, Collapse, Badge } from "antd";
import { Link } from "react-router-dom";
import { formatDateTime } from "../../util/Helpers";
const { Panel } = Collapse;

export class ViewTask extends Component {
	constructor(props) {
		super(props);
		this.state = {
			task: null,
			user: this.props.currentUser,
			loading: false
		};
	}

	componentDidMount() {
		this.setState({ loading: true });
		let taskId = this.props.match.params.taskId || 0;
		let userId = +this.state.user.id;
		taskId = +taskId;
		this.loadTask(userId, taskId);
	}

	loadTask(userId, taskId) {
		fetchUserTask(userId, taskId)
			.then(res => {
				this.setState({ task: res.result, loading: false });
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
					</Panel>
				</Collapse>
				<br />
				<div className="row">
					<div className="col-md-12">
						<Link
							to="/member/tasklist"
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
