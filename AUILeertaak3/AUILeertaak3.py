import http.server as hs;

def run():
    PORT = 8888;

    Handler = hs.SimpleHTTPRequestHandler;

    httpd = hs.HTTPServer(("", PORT), Handler);

    print("Serving at port" + str(PORT));
    httpd.serve_forever();

if __name__ == "__main__":
    run();