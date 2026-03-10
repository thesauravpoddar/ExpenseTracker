# ExpenseTracker

A Spring Boot + MongoDB expense tracking app built with a microservices architecture. 
Each service is containerized with Docker and can be orchestrated using Kubernetes.

## Tech Stack
- **Backend:** Spring Boot (Java)
- **Database:** MongoDB
- **Containerization:** Docker & Docker Compose
- **Orchestration:** Kubernetes

## Services
- **ET-DB** — MongoDB database service
- **ET-SER-DB** — handles communication between services and the DB
- **expservices** — core expense business logic
- **ExpenseReport** — report generation

## Running Locally

Make sure Docker is running, then:
```bash
docker-compose up
```

This spins up all services together.

## Kubernetes Deployment (Minikube)

### 1. Start Minikube
```bash
minikube start
```

If you want to allocate more resources:
```bash
minikube start --cpus=4 --memory=4096
```

### 2. Point Docker to Minikube's registry

This lets Minikube use your locally built images without pushing to Docker Hub:
```bash
eval $(minikube docker-env)
```

### 3. Apply all manifests
```bash
kubectl apply -f .
```

### 4. Check if everything is running
```bash
kubectl get pods
kubectl get services
```

Wait until all pods show `Running`. If something is stuck in `Pending` or 
`CrashLoopBackOff`, check logs with:
```bash
kubectl logs <pod-name>
```

### 5. Access the app

Since we're on Minikube, NodePort or LoadBalancer services need a tunnel:
```bash
minikube service <service-name>
```

This opens the service in your browser automatically. To just get the URL:
```bash
minikube service <service-name> --url
```

### 6. Useful commands while debugging
```bash
# describe a pod to see events/errors
kubectl describe pod <pod-name>

# get inside a running container
kubectl exec -it <pod-name> -- /bin/bash

# restart a deployment
kubectl rollout restart deployment <deployment-name>

# check all resources at once
kubectl get all
```

### 7. Stopping everything
```bash
# delete all deployed resources
kubectl delete -f .

# stop minikube
minikube stop

# if you want to wipe the cluster entirely
minikube delete
```

## Project Structure
```
ExpenseTracker/
├── ET-DB/               
├── ET-SER-DB/           
├── ExpenseReport/       
├── Expensetracker/      
├── expservices/         
├── docker-compose.yml   
└── *.yaml               # Kubernetes deployment & service files
```

## Notes
- Each service has its own Docker image hosted on Docker Hub
- If you're not using `eval $(minikube docker-env)`, make sure images are pushed 
  to Docker Hub before running `kubectl apply`
- MongoDB data won't persist if the pod restarts unless you've set up a PersistentVolume
