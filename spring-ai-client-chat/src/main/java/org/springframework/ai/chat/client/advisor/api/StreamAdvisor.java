/*
 * Copyright 2023-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.ai.chat.client.advisor.api;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import reactor.core.publisher.Flux;

/**
 * Advisor for execution flows ultimately resulting in a streaming call to an AI model.
 *
 * @author Thomas Vitale
 * @since 1.0.0
 */
public interface StreamAdvisor extends StreamAroundAdvisor {

	/**
	 * @deprecated use {@link #adviseStream(ChatClientRequest, StreamAroundAdvisorChain)}
	 */
	@Deprecated
	default Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
		Flux<ChatClientResponse> chatClientResponse = adviseStream(advisedRequest.toChatClientRequest(), chain);
		return chatClientResponse.map(AdvisedResponse::from);
	}

	Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAroundAdvisorChain chain);

}
