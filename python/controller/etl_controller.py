from fastapi import APIRouter, UploadFile, File

router = APIRouter()

@router.post("/etl/process")
def python_file ()