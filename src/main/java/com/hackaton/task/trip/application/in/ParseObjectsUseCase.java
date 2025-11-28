package com.hackaton.task.trip.application.in;

import com.hackaton.task.trip.application.in.command.ParseObjectsCommand;

public interface ParseObjectsUseCase {
	Boolean parseObjectsFromXml(ParseObjectsCommand command) throws Exception;
}
