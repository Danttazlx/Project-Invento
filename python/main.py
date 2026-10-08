from fastapi import FastAPI
from controller.etl_controller import router

app = FastAPI()

app.include_router(router)  