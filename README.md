# ExpenseTracker

A microservices-based expense tracking application built with Spring Boot and MongoDB.

## Tech Stack

- **Backend:** Spring Boot (Java)
- **Database:** MongoDB
- **Containerization:** Docker & Docker Compose
- **Orchestration:** Kubernetes

## Architecture

The application follows a microservices architecture, split into independent services:

| Service | Description |
|--------|-------------|
| `ET-DB` | MongoDB database service |
| `ET-SER-DB` | Service layer connecting to DB |
| `expservices` | Core expense business logic |
| `ExpenseReport` | Report generation service |

Each service has its own Docker image and is managed via Docker Compose locally,
or deployed as a Kubernetes pod using the provided YAML manifests.

## Getting Started

### Prerequisites

- Docker & Docker Compose installed
- Java 17+ (for local dev)
- kubectl (for Kubernetes deployment)

### Run with Docker Compose
```bash
docker-compose up
```

That's it. All services will spin up together.

### Deploy to Kubernetes

Apply each deployment and service manifest:
```bash
kubectl apply -f et-db-deployment.yaml
kubectl apply -f et-db-service.yaml
kubectl apply -f et-ser-deployment.yaml
kubectl apply -f et-ser-service.yaml
kubectl apply -f et-r-deployment.yaml
kubectl apply -f et-r-service.yaml
kubectl apply -f et-uas-deployment.yaml
kubectl apply -f et-uas-service.yaml
kubectl apply -f et-ser-db-deployment.yaml
kubectl apply -f et-ser-db-service.yaml
```

Or apply everything at once:
```bash
kubectl apply -f .
```

## Project Structure
```
ExpenseTracker/
├── ET-DB/                    # DB service
├── ET-SER-DB/                # DB connector service
├── ExpenseReport/            # Reporting service
├── Expensetracker/           # Core tracker service
├── expservices/              # Expense business logic
├── docker-compose.yml        # Local multi-service setup
├── *-deployment.yaml         # Kubernetes deployments
└── *-service.yaml            # Kubernetes services
```

## Notes

- Make sure Docker daemon is running before using `docker-compose up`
- Each service pushes its own image to Docker Hub — check individual service dirs for image names
- Kubernetes manifests assume images are already pushed to your registry
