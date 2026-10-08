from fastapi import APIRouter, UploadFile, File

router = APIRouter()


@router.post("/etl/process")
async def process_etl(file: UploadFile = File(...)):
    return {
        "fileName": file.filename,
        "contentType": file.content_type,
        "status": "PROCESSED"
    }