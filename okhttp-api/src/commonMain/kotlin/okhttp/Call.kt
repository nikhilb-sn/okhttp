/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okhttp

/**
 * A call is a request that has been prepared for execution. A call can be canceled. As this object
 * represents a single request/response pair (stream), it cannot be executed twice.
 */
expect interface Call {
  /** Returns the original request that initiated this call. */
  fun request(): Request

  suspend fun execute(): Response

  /** Cancels the request, if possible. Requests that are already complete cannot be canceled. */
  fun cancel()

  /**
   * Returns true if this call has been either [executed][execute] or [enqueued][enqueue]. It is an
   * error to execute a call more than once.
   */
  fun isExecuted(): Boolean

  fun isCanceled(): Boolean

//  /**
//   * Returns a timeout that spans the entire call: resolving DNS, connecting, writing the request
//   * body, server processing, and reading the response body. If the call requires redirects or
//   * retries all must complete within one timeout period.
//   *
//   * Configure the client's default timeout with [OkHttpClient.Builder.callTimeout].
//   */
//  fun timeout(): Timeout

  /**
   * Create a new, identical call to this one which can be enqueued or executed even if this call
   * has already been.
   */
  fun clone(): Call

  fun interface Factory {
    fun newCall(request: Request): Call
  }
}