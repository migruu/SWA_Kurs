## helm dependency update

update charts/ based on the contents of Chart.yaml

### Synopsis


Update the on-disk dependencies to mirror Chart.yaml.

This command verifies that the required charts, as expressed in 'Chart.yaml',
are present in 'charts/' and are at an acceptable version. It will pull down
the latest charts that satisfy the dependencies, and clean up old dependencies.

On successful update, this will generate a lock file that can be used to
rebuild the dependencies to an exact version.

Dependencies are not required to be represented in 'Chart.yaml'. For that
reason, an update command will not remove charts unless they are (a) present
in the Chart.yaml file, but (b) at the wrong version.


```
helm dependency update CHART [flags]
```

### Options

```
  -h, --help             help for update
      --keyring string   keyring containing public keys (default "C:\\Users\\rasch\\.gnupg\\pubring.gpg")
      --skip-refresh     do not refresh the local repository cache
      --verify           verify the packages against signatures
```

### Options inherited from parent commands

```
      --burst-limit int                 client-side default throttling limit (default 100)
      --debug                           enable verbose output
      --kube-apiserver string           the address and the port for the Kubernetes API server
      --kube-as-group stringArray       group to impersonate for the operation, this flag can be repeated to specify multiple groups.
      --kube-as-user string             username to impersonate for the operation
      --kube-ca-file string             the certificate authority file for the Kubernetes API server connection
      --kube-context string             name of the kubeconfig context to use
      --kube-insecure-skip-tls-verify   if true, the Kubernetes API server's certificate will not be checked for validity. This will make your HTTPS connections insecure
      --kube-tls-server-name string     server name to use for Kubernetes API server certificate validation. If it is not provided, the hostname used to contact the server is used
      --kube-token string               bearer token used for authentication
      --kubeconfig string               path to the kubeconfig file
  -n, --namespace string                namespace scope for this request
      --qps float32                     queries per second used when communicating with the Kubernetes API, not including bursting
      --registry-config string          path to the registry config file (default "C:\\Users\\rasch\\AppData\\Roaming\\helm\\registry\\config.json")
      --repository-cache string         path to the file containing cached repository indexes (default "C:\\Users\\rasch\\AppData\\Local\\Temp\\helm\\repository")
      --repository-config string        path to the file containing repository names and URLs (default "C:\\Users\\rasch\\AppData\\Roaming\\helm\\repositories.yaml")
```

### SEE ALSO

* [helm dependency](helm_dependency.md)	 - manage a chart's dependencies

###### Auto generated by spf13/cobra on 4-May-2024