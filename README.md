# Introduction
It is common for web applications to have logical layers of code, with each layer being
responsible for a specific responsibility.  Requests from application users are handled
by a `Controller` that may invoke one or more `Service`s to perform computations along
with any necessary business logic before returning one or more `Model` objects containing
data relevant to the user request back to the `Controller`.  The `Controller` then
hands the control off to a `Renderer` to merge the `Model`s with `View`s - templates
for generating a response appropriate for the user request.  The final output of the
`Renderer` is usually a character stream appropriate for transmission over the HTTP
protocol, such as, an HTML document, XML, JSON, etc.

[Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
is a popular framework based on the Java programming language that provides an easy-to-use
blueprint for implementing the `Model-View-Controller` pattern and out-of-the-box
integration with multiple Java based web rendering engines.

One of the interesting features provided by Spring MVC is the caching of the final output
of an HTTP request.  This feature is very useful for web applications that have certain
pages which are frequently accessed but are expensive to render, possibly because the
nature and amount of computation required to render the pages is extensive.  Developers
frequently use this feature to generate such expensive pages once (or periodically)
instead of on every user request to develop high performance websites using Spring MVC.

However, there are cases where it is not preferable to cache the final output of an HTTP
request, the reasons ranging from slight variations in the output on a per user basic,
access checks on pages or parts of pages, etc.  If the final output is cached in such cases,
the application would start presenting the cached output to every user after it has cached
the output for the first time.  This may either cause privileged, user-specific information
to leak to multiple users or display incorrect information to users or allow users to view
and possible access information intended for other users.

If an application is well designed and the layers have been properly identified and
implemented, an option is to cache the `Model` objects returned by the `Service` layer
(or its equivalent).  The `Controller` layer can then use cached `Model` objects to perform
any user checks, etc. and the only thing required to render the final output for the user
request is restricted to merging the `Model` objects with `View` templates.

This application demonstrates caching of model objects in a Spring MVC application.  The
controller layer gets data from a service layer, the output of which is cached.  Any changes
to the data by the user invalidates the cache so that end users always get updated results.

# Running the application
`mvn clean tomcat7:run` will start an embedded Tomcat instance.  The application can then
be accessed using a web browser on [http://localhost:8080](http://localhost:8080).

# License
This sample application and its associated source code in its entirety is being made
available under the following licensing terms.

    Copyright (C) 2014

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in the
    Software without restriction, including without limitation the rights to use, copy,
    modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
    and to permit persons to whom the Software is furnished to do so, subject to the
    following conditions:

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
    CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
    OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
